package hu.inf.unideb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import hu.inf.unideb.Items.Inventory;
import hu.inf.unideb.Items.Item;
import hu.inf.unideb.Items.TooMuchItemsException;
import hu.inf.unideb.itemOperations.ItemFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public final class App {

    /**
     * Prompt prompting the player to give a command.
     */
    private static final String PROMPTSTRING = "Make a choice:\n1.,Instantiate"
            + " an item\n2.,See inventory\n3.,Crafting\n4.,quit";

    /**
     * The number of the instantiation command.
     */
    private static final int INSTATNTIATIONCOMMANDNUMBER = 1;

    /**
     * The number of the command showing the inventory.
     */
    private static final int INVENTORYSHOWCOMMANDNUMBER = 2;

    /**
     * The number of the crafting command..
     */
    private static final int CRAFTINGCOMMANDNUMBER = 3;

    /**
     * The number of the command quitting the application.
     */
    private static final int QUITCOMMANDNUMBER = 4;

    /**
     * Dummy constructor to prevent instantiation of main class.
     */
    private App() {
    }

    /**
     * Instantiates an item for demo purposes.
     * @param id The id of the weapon to instantiate.
     * @return an instantiated item.
     */
    private static Item itemInstantiation(final int id) {
        ItemFactory factory = new ItemFactory();
        System.out.println();
        return factory.instantiateItem(id);
    }

    /**
     * Method that prints out the contents of the players inventory.
     *
     * @param inv The inventory which's contents to print out.
     */
    private static void printOutInventory(final Inventory inv) {
        ArrayList<Item> items = inv.getBackpackItems();
        if (items.size() > 0) {
            System.out.println("Player's Inventory:");
            for (Item i : items) {
                System.out.println("--" + i.getName()
                                + " " + i.getDurability()
                                + "% " + i.getWeight());
            }
        } else {
            System.out.println("The inventory has no items.");
        }
        System.out.println();
    }

    /**
     * The main Function. Takes instructions from the user and executes them.
     *
     * @param args The argument vector of the application.
     */
    public static void main(final String[] args) {
        Logger logger = LoggerFactory.getLogger("MainClass");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        boolean running = true;
        Inventory inventory = new Inventory();
        int command = 0;
        try {
            while (running) {
                System.out.println(PROMPTSTRING);
                command = Integer.parseInt(reader.readLine());
                switch (command) {
                case INSTATNTIATIONCOMMANDNUMBER:
                    try {
                        System.out.println("Please give an item ID:");
                        int id = Integer.parseInt(reader.readLine());
                        inventory.addItem(itemInstantiation(id));
                    } catch (TooMuchItemsException e) {
                        logger.warn("The player has too much items.");
                    }
                    break;
                case INVENTORYSHOWCOMMANDNUMBER:
                    printOutInventory(inventory);
                    break;
                case CRAFTINGCOMMANDNUMBER:
                    System.out.println("crafting");
                    break;
                case QUITCOMMANDNUMBER:
                    running = false;
                    break;
                default:
                    System.out.println("Unknown Command");
                }
            }
            reader.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
