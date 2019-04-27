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
     * The percentage of damage reduction.
     */
    private double damageReduction;

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
}
