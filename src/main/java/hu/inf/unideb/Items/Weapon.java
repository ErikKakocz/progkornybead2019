package hu.inf.unideb.Items;

/**
 *
 * Class representing weapons. This class is derived from the class
 * {@link hu.inf.unideb.Items.Item}.
 *
 * @author Erik Kakócz
 *
 */

public class Weapon extends Item {

    /**
     * Base damage the weapon deals to the enemy.
     */
    private int attack;

    /**
     * Public constructor for the weapon class.
     */
    Weapon() {
        this.attack = 1;
    }

    /**
     * Public constructor for the weapon class.
     *
     * @param weaponAttack The Base damage this weapon deals.
     */
    Weapon(final int weaponAttack) {
        this.attack = weaponAttack;
    }

    /**
     * Getter for the field {@link attack}.
     *
     * @return attack The attack value of this weapon.
     */
    public final int getAttack() {
        return attack;
    }

    /**
     * Setter for the field {@link attack}.
     * @param weaponAttack The new attack value of this weapon.
     */
    public final void setAttack(final int weaponAttack) {
        this.attack = weaponAttack;
    }

}
