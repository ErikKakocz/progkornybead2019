package hu.inf.unideb.Items;

/**
 *
 * Class representing armor. Armor items reduce the damage the player receives
 * This class is a derivative of the {@link hu.inf.unideb.Items.Item} class.
 *
 * @author Erik Kakócz
 *
 */


public class Armor extends Item {

    /**
     * Id for the parameterless constructor.
     */
    private static final int DUMMYID = -1;

    /**
     * Weight for the parameterless constructor.
     */
    private static final int DUMMYWEIGHT = 10;

    /**
     * Durability for the parameterless constructor.
     */
    private static final int DUMMYDURABILITY = 100;

    /**
     * Damage reduction for the parameterless constructor.
     */
    private static final int DUMMYDAMAGEREDUCTION = 10;

    /**
     * Movement impairment for the parameterless constructor.
     */
    private static final int DUMMYMOVEMENTIMPAIRMENT = 15;

    /**
     * The percentage of damage reduction.
     */
    private double damageReduction;

    /**
     * The percentage with which this armor slows the down player.
     */
    private int movementImpairment;


    /**
     * Paramaterless constructor for the class Armor. It instantiates an armor
     * object with a damage reduction of 10 and movement impairment of 15.
     */
    public Armor() {
        super(DUMMYID, DUMMYWEIGHT, DUMMYDURABILITY, "DummyArmor");
        damageReduction = DUMMYDAMAGEREDUCTION;
        movementImpairment = DUMMYMOVEMENTIMPAIRMENT;
    }

    /**
     * Constructor for the class Armor.
     * @param id The unique id of the armor item
     * @param weight the weight of the armor item.
     * @param durability the durability of the armor
     * @param name the name of the armor item.
     * @param damageReduct The percentage of damage reduction the armor
     * provides
     * @param movementImp The percentage of movement impairment of the
     * armor
     */
    public Armor(final int id, final double weight, final int durability,
                final String name, final int damageReduct,
                final int movementImp) {
        super(id, weight, durability, name);
        damageReduction = damageReduct;
        movementImpairment = movementImp;
    }

    /**
     * Returns the percentage of damage reduction of the current armor.
     *
     * @return the damage reduction of the armor
     */
    public final double getDamageReduction() {
        return damageReduction;
    }

    /**
     * Set the percentage of damage reduction of the current armor.
     *
     * @param newDamageReduction the damage reduction of the armor
     */
    public final void setDamageReduction(final double newDamageReduction) {
        this.damageReduction = newDamageReduction;
    }

    /**
     * Returns the percentage of movement impairment of the current armor.
     *
     * @return the movementImpairment
     */
    public final int getMovementImpairment() {
        return movementImpairment;
    }

    /**
     * Sets the percentage of movement impairment of the current armor.
     *
     * @param movementImp the movementImpairment to set
     */
    public final void setMovementImpairment(final int movementImp) {
        this.movementImpairment = movementImp;
    }


}
