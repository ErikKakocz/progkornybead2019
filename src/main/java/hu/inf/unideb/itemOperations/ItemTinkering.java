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

    public ItemTinkering() {
        logger = LoggerFactory.getLogger(ItemTinkering.class);
    }

    public String getCraftableItems(Inventory inv) {
        ItemFactory factory=new ItemFactory();
        ArrayList<Item> items = inv.getBackpackItems();
        ArrayList<Recipe> recipes=factory.getRecipes();
        String result=null;
        for(int i=0;i<items.size();i++) {
            Item itemOne=items.get(i);
            for(int j=i+1;j<items.size();j++) {
                Item itemTwo=items.get(j);
                for(Recipe recipe:recipes) {
                    int idOne=itemOne.getId();
                    int idTwo=itemTwo.getId();
                    if(recipe.getIngredientOneId()==idOne &&
                        (recipe.getIngredientTwoId()==idTwo||
                        recipe.getIngredientTwoId()==-1)) {
                        result=result+"id:"+recipe.getItemId()+". "+
                                itemOne.getName()+"+"+
                                itemTwo.getName()+"->"+
                                factory.getItemNameById(recipe.getItemId())+
                                "\n";
                    }
                }
            }
        }
        
        return result;
    }

    public Inventory craftItem(final Item ingredientOne,
            final Item ingredientTwo,final Inventory inv) {
        ItemFactory factory=new ItemFactory();
        ArrayList<Recipe> recipes=factory.getRecipes();
        int idOne=ingredientOne.getId();
        int idTwo=ingredientTwo.getId();
        for(int i=0;i<recipes.size();i++) {
            if(recipes.get(i).getIngredientOneId()==idOne &&
               recipes.get(i).getIngredientTwoId()==idTwo) {
                inv.getBackpackItems().remove(ingredientOne);
                inv.getBackpackItems().remove(ingredientTwo);
                int id=recipes.get(i).getItemId();
                int itemDurability=(int)Math.ceil((double)
                        (ingredientOne.getDurability()
                        +ingredientTwo.getDurability()/2)*1.2);
                Item item=factory.instantiateItem(id);
                if(item==null)
                    item=factory.instantiateWeapon(id,itemDurability);
                if(item==null)
                    item=factory.instantiateArmor(id,itemDurability);
                if(item==null)
                    break;
                try {
                    inv.addItem(item);
                } catch (TooMuchItemsException e) {
                    logger.error(e.getMessage()+"Crafting didn't succeed.");
                }
            }
        }
        return inv;
    }
}

