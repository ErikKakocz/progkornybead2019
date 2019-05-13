package hu.inf.unideb.itemOperations;

public class Recipe {

    private int ingredientOneId;

    private int ingredientTwoId;

    private int itemId;
    
    private int amount;
    
    public Recipe() {
        
    }
    
    public Recipe(final int ingOne,final int ingTwo,
                   final int id,final int am) {
        ingredientOneId=ingOne;
        ingredientTwoId=ingTwo;
        itemId=id;
        amount=am;
    }

    /**
     * @return the ingredientOneId
     */
    public int getIngredientOneId() {
        return ingredientOneId;
    }

    /**
     * @param ingredientOneId the ingredientOneId to set
     */
    public void setIngredientOneId(int ingredientOneId) {
        this.ingredientOneId = ingredientOneId;
    }

    /**
     * @return the ingredientTwoId
     */
    public int getIngredientTwoId() {
        return ingredientTwoId;
    }

    /**
     * @param ingredientTwoId the ingredientTwoId to set
     */
    public void setIngredientTwoId(int ingredientTwoId) {
        this.ingredientTwoId = ingredientTwoId;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the itemId
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Recipe [ingredientOneId=" + ingredientOneId + ", ingredientTwoId=" + ingredientTwoId + ", itemId="
                + itemId + ", amount=" + amount + "]";
    }
    
    
}
