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
        String result="";
        for(int i=0;i<items.size();i++) {
            Item itemOne=items.get(i);
            for(int j=i;j<items.size();j++) {
                Item itemTwo=items.get(j);
                for(Recipe recipe:recipes) {
                    //logger.info(recipe.toString());
                    int idOne=itemOne.getId();
                    int idTwo=itemTwo.getId();
                    if(j==i) {
                        idTwo=-2;
                    }
                    if(recipe.getIngredientOneId()==idOne) {
                        if(recipe.getIngredientTwoId()==-1) {
                            result=result+"id:"+recipe.getItemId()+". "+
                                    itemOne.getName()+"->"+
                                    factory.getItemNameById(recipe.getItemId())+
                                    "\n";
                        }else if(recipe.getIngredientTwoId()==idTwo) {
                        result=result+"id:"+recipe.getItemId()+". "+
                                itemOne.getName()+"+"+
                                itemTwo.getName()+"->"+
                                factory.getItemNameById(recipe.getItemId())+
                                "\n";
                        }
                    }
                }
            }
        }
        return result;
    }

    public Inventory craftItem(final int id,final Inventory inv) {
        ItemFactory factory=new ItemFactory();
        Recipe recipe = factory.getCraftingRecipeById(id);
        ArrayList<Item> backpack=inv.getBackpackItems();
        int ItemIndexOne=-1;
        int ItemIdOne=-1;
        int ItemIndexTwo=-1;
        int ItemIdTwo=-1;
        for(int i=0;i<backpack.size();i++) {
            logger.info("Looking for first ingredient: "+i);
            int itemId=backpack.get(i).getId();
            if(itemId==recipe.getIngredientOneId()) {
                logger.info("found first ingredient at: "+i);
                ItemIndexOne=i;
                ItemIdOne=itemId;
                break;
            }
        }
        for(int i=0;i<backpack.size();i++) {
            logger.info("Looking for second ingredient: "+i);
            int itemId=backpack.get(i).getId();
            if(itemId==recipe.getIngredientTwoId()&&
                    ItemIndexOne!=i ) {
                logger.info("found second ingredient at: "+i);
                ItemIndexTwo=i;
                ItemIdTwo=itemId;
                break;
            }
        }
        logger.info(ItemIdOne+" "+recipe.getIngredientOneId()+" "+
                ItemIdTwo+" "+recipe.getIngredientTwoId());
        if(ItemIdOne==recipe.getIngredientOneId()&&
                ItemIdTwo==recipe.getIngredientTwoId()) {
            logger.info("We have everything");
            Item item=factory.create(id);
            inv.dropItem(ItemIndexOne);
            if(ItemIndexTwo>-1) {
                inv.dropItem(ItemIndexTwo);
            }
            try {
                inv.addItem(item);
            } catch (TooMuchItemsException e) {
                logger.info("Inventory is full");
            }
            
        }
        logger.info("Crafting in progress");
        return inv;
    }

    public Item repairItem(Item item) {
        item.setDurability(100);
        return item;
    }
}

