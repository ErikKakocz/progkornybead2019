package hu.inf.unideb.itemOperations;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.inf.unideb.Items.Inventory;
import hu.inf.unideb.Items.Item;
import hu.inf.unideb.Items.TooMuchItemsException;

/**
 * Implementation of the features the player can do with the items.
 * @author Erik Kakócz
 *
 */
public class ItemTinkering {

    /**
     * Logger used for logging.
     */
    private static Logger logger;

    /**
     * Constructor for the class.
     */
    public ItemTinkering() {
        logger = LoggerFactory.getLogger(ItemTinkering.class);
    }

    /**
     * Creates a list of all the items the layer can craft from the items
     * in the inventory.
     * @param inv The {@link Inventory} containing the crafting ingredients.
     * @return A string with all the craftable items and their ingredients.
     */
    public final String getCraftableItems(final Inventory inv) {
        ItemFactory factory = new ItemFactory();
        ArrayList<Item> items = inv.getBackpackItems();
        ArrayList<Recipe> recipes = factory.getRecipes();
        ArrayList<Recipe> recipesToIgnore = new ArrayList<Recipe>();
        String result = "";
        for (int i = 0; i < items.size(); i++) {
            Item itemOne = items.get(i);
            for (int j = 0; j < items.size(); j++) {
                Item itemTwo = items.get(j);
                for (Recipe recipe:recipes) {
                    if (recipesToIgnore.contains(recipe)) {
                        continue;
                    }
                    //logger.info(recipe.toString());
                    int idOne = itemOne.getId();
                    int idTwo = itemTwo.getId();
                    if (j == i) {
                        idTwo = -1;
                    }
                    if (recipe.getIngredientOneId() == idOne) {
                        if (recipe.getIngredientTwoId() == -1) {
                            result = result + "id:" + recipe.getItemId() + ". "
                                    + itemOne.getName() + "->"
                                    + factory.getNameById(recipe.getItemId())
                                    + "\n";
                            recipesToIgnore.add(recipe);
                        } else if (recipe.getIngredientTwoId() == idTwo) {
                            result = result + "id:" + recipe.getItemId() + ". "
                                + itemOne.getName() + "+"
                                + itemTwo.getName() + "->"
                                + factory.getNameById(recipe.getItemId())
                                + "\n";
                            recipesToIgnore.add(recipe);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Crafts an item using one ore two ingredients from the players inventory.
     * @param id The id of the item to craft.
     * @param inv The inventory containing the crafting ingredients.
     * @return The inventory containing the crafted item.
     */
    public final Inventory craftItem(final int id, final Inventory inv) {
        ItemFactory factory = new ItemFactory();
        Recipe recipe = factory.getCraftingRecipeById(id);
        ArrayList<Item> backpack = inv.getBackpackItems();
        Item itemOne = null;
        Item itemTwo = null;
        for (Item i:backpack) {
            if (i.getId() == recipe.getIngredientOneId()) {
                itemOne = i;
            }
        }
        for (Item i:backpack) {
            if (i.getId() == recipe.getIngredientTwoId()) {
                itemTwo = i;
            }
        }
        if (itemOne.getId() == recipe.getIngredientOneId()
                && itemTwo.getId() == recipe.getIngredientTwoId()) {
            logger.info("We have everything");
            Item item;
            inv.dropItem(backpack.indexOf(itemOne));
            if (itemTwo != null) {
                inv.dropItem(inv.getBackpackItems().indexOf(itemTwo));
            }
            for (int i = 0; i < recipe.getAmount(); i++) {
                item = factory.create(id);
                try {
                    inv.addItem(item);
                } catch (TooMuchItemsException e) {
                    logger.info("Inventory is full");
                }
            }
        }
        logger.info("Crafting in progress");
        return inv;
    }

    /**
     * Restores the durability of an {@link Item} to 100%.
     * @param item The {@link Item} to repair.
     * @return The repaired {@link Item}.
     */
    public final Item repairItem(final Item item) {
        item.setDurability(Item.MAXIMUM_DURABILITY);
        return item;
    }
}

