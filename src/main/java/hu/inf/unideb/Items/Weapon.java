package hu.inf.unideb.Items;

/**
 *
 * Class representing weapons that are used by the player to inflict damage to
 * enemy entities in the game. This class is derived from the class
 * {@link hu.inf.unideb.Items.Item}.
 *
 * @author Erik Kakócz
 *
 */

public class Weapon extends Item {

    /**
     * Base damage the weapon deals to the enemy.
     */
    private double attack;

    /**
     * The attack speed of the weapon. This value shows how frequent the player
     * can attack with the weapon.
     */
    private double speed;

    /**
     * The type of the weapon.
     */
    private WeaponType type;

    /**
     * Public parameterless constructor for the weapon class.
     */
    public Weapon() {
        this.attack = 1;
    }

    /**
     * Public constructor for the weapon class.
     *
     * @param id the unique id of the weapon.
     * @param weight the weight of the weapon.
     * @param durability The durability of the weapon.
     * @param name The name of the weapon object.
     * @param weaponAttack The Base damage this weapon deals.
     * @param wspeed The attack speed of the weapon object.
     * @param wtype The type of the weapon object.
     */
    public Weapon(final int id, final double weight, final int durability,
                  final String name, final double weaponAttack,
                  final double wspeed, final WeaponType wtype) {
        super(id, weight, durability, name);
        this.attack = weaponAttack;
        this.speed = wspeed;
        this.type = wtype;
    }

    /**
     * Getter for the field {@link attack}.
     *
     * @return attack The attack value of this weapon.
     */
    public final double getAttack() {
        return attack;
    }

    /**
     * Setter for the field {@link attack}.
     * @param weaponAttack The new attack value of this weapon.
     */
    public final void setAttack(final double weaponAttack) {
        this.attack = weaponAttack;
    }

    /**
     * Getter for the field {@link speed}.
     *
     * @return speed The attack speed of this weapon.
     */
    public final double getSpeed() {
        return speed;
    }

    /**
     * Setter for the field {@link speed}.
     * @param weaponSpeed The new type of this weapon.
     */
    public final void setSpeed(final double weaponSpeed) {
        this.speed = weaponSpeed;
    }

    /**
     * Getter for the field {@link type}.
     *
     * @return type The type of this weapon.
     */
    public final WeaponType getType() {
        return type;
    }

    /**
     * Setter for the field {@link type}.
     * @param weaponType The new type of this weapon.
     */
    public final void setType(final WeaponType weaponType) {
        this.type = weaponType;
    }

}
