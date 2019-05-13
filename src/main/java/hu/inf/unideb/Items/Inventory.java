package hu.inf.unideb.Items;

import java.util.ArrayList;

/**
 *
 * Class representing player inventory used to contain items
 * the player collects.
 *
 * @author Erik Kakócz
 * @see hu.inf.unideb.Items.Item
 */

public class Inventory {

    /**
     * The default value of the field maximumWeight, if none is given.
     */
    private static final double DEFAULT_MAXIMUM_WEIGHT = 80.0;
    /**
     * The maximum weight this inventory can hold, which is either
     * calculated from various factors, or the default value
     * {@link DEFAULT_MAXIMUM_WEIGHT} is used.
     */
    private double maximumWeight;
    /**
     * The weight of the items in the players backpack summarized. The player
     * can't carry more than the {@link maximumWeight} value.
     */
    private double carriedWeight;

    /**
     * An {@link java.util.ArrayList} containing the {@link Item} objects and
     * its derivatives.
     */
    private ArrayList<Item> backpack;
    /**
     * public constructor for the inventory class. The constructor
     * automatically sets the maximumWeight variable to
     * {@link DEFAULT_MAXIMUM_WEIGHT} and the carriedWeight variable to 0.
     */
    public Inventory() {
        backpack = new ArrayList<Item>();
        carriedWeight = 0.0;
        maximumWeight = DEFAULT_MAXIMUM_WEIGHT;
        backpack.add(new Item());
        backpack.add(new Item());
    }
    /**
     * public constructor for the inventory class. The carriedWeight is
     * automatically set set to 0.0, while the maximum weight is given as an
     * argument.
     *
     * @param maxWeight The maximum weight a player can carry in his/her
     * inventory.
     */
    Inventory(final double maxWeight) {
        backpack = new ArrayList<Item>();
        carriedWeight = 0.0;
        maximumWeight = maxWeight;
        backpack.add(new Item());
        backpack.add(new Item());
    }


    /**
     * Adds an item to the backpack of the player.
     * @param itemToAdd The item the player wishes to add to the inventory.
     * @throws TooMuchItemsException If the weight of the item to add and the
     * already carried weight is more than the maximumWeight.
     */
    public final void addItem(final Item itemToAdd)
            throws TooMuchItemsException {
        if (carriedWeight + itemToAdd.getWeight() <= maximumWeight) {
            backpack.add(itemToAdd);
            if(backpack.get(0).getName().equals("Dummy")) {
                backpack.remove(0);
            }
            carriedWeight += itemToAdd.getWeight();
        } else {
            throw new TooMuchItemsException();
        }
    }

    /**
     * Removes an item from the backpack.
     * @param index The index of the item the player wishes to remove from the
     * inventory.
     * @return The item the player wants to drop.
     */
    public final Item dropItem(final int index) {
        Item itemToDrop = backpack.remove(index);
        carriedWeight -= itemToDrop.getWeight();
        return itemToDrop;
    }
    /**
     * @return the maximum weight the player can carry.
     */
    public final double getMaximumWeight() {
        return maximumWeight;
    }
    /**
     * @param maxWeight the maximumWeight to set
     */
    public final void setMaximumWeight(final double maxWeight) {
        this.maximumWeight = maxWeight;
    }
    /**
     * @return the carriedWeight
     */
    public final double getCarriedWeight() {
        return carriedWeight;
    }
    /**
     * @param carryWeight the carriedWeight to set
     */
    public final void setCarriedWeight(final double carryWeight) {
        this.carriedWeight = carryWeight;
    }

    /**
     *
     * @return An Arraylist with the items in the players backpack.
     */
    public final ArrayList<Item> getBackpackItems() {
        return backpack;
    }
}
