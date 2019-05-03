package hu.inf.unideb.Items;

/**
 * Class representing in-game items. All kinds of items in the game
 *
 * @author Erik Kakócz
 */

public class Item {

    /**
     * The maximum durability an item can have.
     */
    public static final int MAXIMUM_DURABILITY = 100;

    /**
     * The minimal weight an item can have.
     */
    private static final int MINIMAL_WEIGHT = 0;

    /**
     * The unique ID of the item.
     */
    private int id;

    /**
     * The weight of the item. A player can only hold a predefined amount of
     * items in his/her inventory.
     */
    private double weight;

    /**
     * The durability of the item. Show how much the item can be used before
     * becoming useless. In case of a {@link ConsumableItem} this shows the
     * quantity of the item.
     */
    private int durability;

    /**
     * The name of the item that will be shown to the player in-game.
     */
    private String name;

    /**
     * public parameterless constructor for the item class. It initializes an
     * item with the id -1, a weight of 0, a durability of 100% and the name
     * "Dummy".
     */
    public Item() {
        this.id = -1;
        this.weight = MINIMAL_WEIGHT;
        this.durability = MAXIMUM_DURABILITY;
        this.name = "Dummy";
    }

    /**
     * public constructor of the Item superclass.
     *
     * @param itemId The unique Id of the item to instantiate.
     * @param itemWeight the weight of the item.
     * @param itemDurability The initial durability of the item.
     * @param itemName The name of the item to instantiate.
     */
    public Item(final int itemId, final double itemWeight,
            final int itemDurability, final String itemName) {
        super();
        this.id = itemId;
        this.weight = itemWeight;
        this.durability = itemDurability;
        this.name = itemName;
    }

    /**
     * Getter for the item's id field.
     * @return The id of the item.
     */
    public final int getId() {
        return id;
    }

    /**
     * Setter for the item's id field.
     * @param itemId The id of the item.
     */
    public final void setId(final int itemId) {
        this.id = itemId;
    }

    /**
     * Getter for the item's durability field.
     * @return The durability of the item.
     */
    public final int getDurability() {
        return durability;
    }

    /**
     * Setter for the item's durability field.
     * @param itemDurability The durability of the item.
     */
    public final void setDurability(final int itemDurability) {
        this.durability = itemDurability;
    }

    /**
     * Getter for the item's weight field.
     * @return The weight of the item.
     */
    public final double getWeight() {
        return weight;
    }

    /**
     * Setter for the item's weight field.
     * @param itemWeight The durability of the item.
     */
    public final void setWeight(final int itemWeight) {
        this.weight = itemWeight;
    }

    /**
     * Getter for the item's name field.
     * @return The name of the item.
     */
    public final String getName() {
        return name;
    }

    /**
     * Setter for the item's name field.
     * @param itemName The name of the item.
     */
    public final void setName(final String itemName) {
        this.name = itemName;
    }
}
