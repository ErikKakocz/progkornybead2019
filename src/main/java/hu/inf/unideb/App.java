package hu.inf.unideb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import hu.inf.unideb.Items.Inventory;
import hu.inf.unideb.Items.Item;
import hu.inf.unideb.Items.TooMuchItemsException;
import hu.inf.unideb.itemOperations.ItemFactory;
import hu.inf.unideb.itemOperations.ItemTinkering;

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
    private static final String PROMPTSTRING = "Make a choice:\n1.,Add an item"
            + " to inventory\n2.,See inventory\n3.,Crafting\n4.Repair item"
            + "\n5.,quit";

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
     * The number of the repair command..
     */
    private static final int REPAIRCOMMANDNUMBER = 4;

    /**
     * The number of the command quitting the application.
     */
    private static final int QUITCOMMANDNUMBER = 5;

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
        return factory.create(id);
    }

    /**
     * Method that prints out the contents of the players inventory.
     *
     * @param inv The inventory which's contents to print out.
     */
    private static void printOutInventory(final Inventory inv) {
        ArrayList<Item> items = inv.getBackpackItems();
        System.out.println("Player's Inventory:");
        int counter = 1;
        if (items.size() > 0) {
            for (Item i : items) {
                if (i.getId() != Item.DUMMYID) {
                    System.out.println(counter + ".--" + i.getName()
                                    + " " + i.getDurability()
                                    + "% " + i.getWeight());
                    counter++;
                }
            }
            System.out.println("Total weight of the items: " + inv
                    .getCarriedWeight());
        } else {
            System.out.println("The inventory has no items.");
        }
        System.out.println();
    }

    /**
     * Writes out the possible items that could be crafted from the ingredients
     * present in this instance of inventory.
     * @param inv The inventory which contains the ingredients for crafting.
     * @throws NoCraftablesException If nothing could be crafted from the
     * given items.
     */
    public static void printCraftables(final Inventory inv)
            throws NoCraftablesException {
        ItemTinkering tinkerer = new ItemTinkering();
        String list = tinkerer.getCraftableItems(inv);
        System.out.println(list);
        if (list == "") {
            System.out.println("No availible craftings");
            throw new NoCraftablesException();
        }

    }

    /**
     * Crafts an item using the ItemTinkerer class's craftItem method.
     * @param id the id of the item to craft.
     * @param inv The inventory that contains the crafting materials.
     * @return The inventory with the newly crafted item and without the
     * ingredients used in the crafting.
     */
    public static Inventory craftItem(final int id, final Inventory inv) {
        ItemTinkering tinkerer = new ItemTinkering();
        return tinkerer.craftItem(id, inv);
    }

    /**
     * Repairs an item in the players inventory using the ItemTinkerer class's
     * repair method.
     * @param inv the inventory that contains the item to repair
     * @param index the index of the item to repair
     * @return The inventory with the repaired item.
     */
    public static Inventory repair(final Inventory inv, final int index) {
        ItemTinkering tinkerer = new ItemTinkering();
        try {
            inv.addItem(tinkerer.repairItem(inv.dropItem(index)));
        } catch (TooMuchItemsException e) {
            Logger logger = LoggerFactory.getLogger(App.class);
            logger.error("Too much items!");
        }
        return inv;
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
                        logger.warn("The players inventory "
                                + "has too much weight.");
                    }
                    break;
                case INVENTORYSHOWCOMMANDNUMBER:
                    printOutInventory(inventory);
                    break;
                case CRAFTINGCOMMANDNUMBER:
                    System.out.println("Please give an item ID:");
                    try {
                    printCraftables(inventory);
                    } catch (NoCraftablesException e) {
                        logger.info("There are no craftables!");
                        break;
                    }
                    int id = Integer.parseInt(reader.readLine());
                    inventory = craftItem(id, inventory);
                    break;
                case REPAIRCOMMANDNUMBER:
                    System.out.println("Select item to repair:(-1 to exit)");
                    printOutInventory(inventory);
                    int index = Integer.parseInt(reader.readLine());
                    if (index < 0) {
                        break;
                    }
                    repair(inventory, index);
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
