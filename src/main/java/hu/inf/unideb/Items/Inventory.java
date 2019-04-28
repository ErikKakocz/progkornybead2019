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

class Inventory {

    /**
     * The default value of the field maximumWeight, if none is given.
     */
    private final static double DEFAULT_MAXIMUM_WEIGHT = 80.0;
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
    Inventory() {
        backpack=new ArrayList<Item>();
        carriedWeight=0.0;
        maximumWeight=DEFAULT_MAXIMUM_WEIGHT;
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
        backpack=new ArrayList<Item>();
        carriedWeight=0.0;
        maximumWeight=maxWeight;
    }


}
