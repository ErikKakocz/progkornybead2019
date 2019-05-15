package hu.inf.unideb.itemOperations;

/**
 * Class representing crafting recipes.
 * @author erik
 *
 */
public class Recipe {

    /**
     * The unique id of the first ingredient needed to craft the item.
     */
    private int ingredientOneId;

    /**
     * The unique id of the second ingredient needed to craft the item.
     * In the case of items that are crafted from a single ingredient, this
     * value is -1.
     */
    private int ingredientTwoId;

    /**
     * The unique id of the item that can be crafted from this recipe.
     */
    private int itemId;

    /**
     * The amount of items that are created during crafting.
     */
    private int amount;

    /**
     * Parameterless constructor for the class.
     */
    public Recipe() {

    }

    /**
     * Constructor for the class.
     * @param ingOne The first ingredient of the crafting recipe.
     * @param ingTwo The second ingredient of the crafting recipe.
     * @param id The id of the crafted item.
     * @param am The amount of the crafted items.
     */
    public Recipe(final int ingOne, final int ingTwo,
                   final int id, final int am) {
        ingredientOneId = ingOne;
        ingredientTwoId = ingTwo;
        itemId = id;
        amount = am;
    }

    /**
     * @return the ingredientOneId
     */
    public final int getIngredientOneId() {
        return ingredientOneId;
    }

    /**
     * @param ingredientId the ingredientOneId to set
     */
    public final void setIngredientOneId(final int ingredientId) {
        this.ingredientOneId = ingredientId;
    }

    /**
     * @return the ingredientTwoId
     */
    public final int getIngredientTwoId() {
        return ingredientTwoId;
    }

    /**
     * @param ingredientId the ingredientTwoId to set
     */
    public final void setIngredientTwoId(final int ingredientId) {
        this.ingredientTwoId = ingredientId;
    }

    /**
     * @return the amount
     */
    public final int getAmount() {
        return amount;
    }

    /**
     * @param am the amount to set
     */
    public final void setAmount(final int am) {
        this.amount = am;
    }

    /**
     * @return the itemId
     */
    public final int getItemId() {
        return itemId;
    }

    /**
     * @param id the itemId to set
     */
    public final void setItemId(final int id) {
        this.itemId = id;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
        return "Recipe [ingredientOneId=" + ingredientOneId
                + ", ingredientTwoId=" + ingredientTwoId + ", itemId="
                + itemId + ", amount=" + amount + "]";
    }
}
