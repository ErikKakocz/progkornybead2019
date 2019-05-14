package hu.inf.unideb.test;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import hu.inf.unideb.Items.Item;
import hu.inf.unideb.itemOperations.ItemFactory;
import hu.inf.unideb.itemOperations.Recipe;

public class ItemFactoryTest {
    
    @Test
    public void factoryShouldReturnRecipeForMoebiousDagger() {
        ItemFactory factory=new ItemFactory();
        Recipe recipe=factory.getCraftingRecipeById(21);
        assert(recipe.getIngredientOneId()==2);
        assert(recipe.getIngredientTwoId()==8);
        assert(recipe.getItemId()==21);
        assert(recipe.getAmount()==1);
    }

    @Test
    public void getItemNameByIdShouldReturnTheNameOfMetalChunk() {
        ItemFactory factory=new ItemFactory();
        assert(factory.getNameById(6).equals("metal chunk"));
    }
    
    @Test
    public void getItemNameByIdShouldReturnTheNameOfMoebiousDagger() {
        ItemFactory factory=new ItemFactory();
        assert(factory.getNameById(21).equals("Moebious dagger"));
    }

    @Test
    public void getItemNameByIdShouldReturnTheNameOfCasualCloth() {
        ItemFactory factory=new ItemFactory();
        assert(factory.getNameById(17).equals("Casual cloth"));
    }

    @Test
    public void getItemNameByIdShouldReturnNull() {
        ItemFactory factory=new ItemFactory();
        assertNull(factory.getNameById(63));
    }

    @Test
    public void instantiateItemShouldReturnHeavyHandle() {
        ItemFactory factory=new ItemFactory();
        Item item=factory.create(4);
        assert(item.getName().equals("heavy handle"));
    }

    @Test
    public void instantiateWeaponShouldReturnSwordOfYadun() {
        ItemFactory factory=new ItemFactory();
        Item item=factory.create(22);
        assert(item.getName().equals("Sword of Yadun"));
    }
    
    @Test
    public void instantiateWeaponShouldReturnMoebiousDagger() {
        ItemFactory factory=new ItemFactory();
        Item item=factory.create(21);
        assert(item.getName().equals("Moebious dagger"));
    }
    
    @Test
    public void instantiateWeaponShouldReturnHeavySaber() {
        ItemFactory factory=new ItemFactory();
        Item item=factory.create(23);
        assert(item.getName().equals("Heavy Saber"));
    }

    @Test
    public void instantiateWeaponShouldReturnStaffOfYamabushies() {
        ItemFactory factory=new ItemFactory();
        Item item=factory.create(24);
        assert(item.getName().equals("Staff of the Yamabushies"));
    }

    @Test
    public void instantiateWeaponShouldReturnWoodenBow() {
        ItemFactory factory=new ItemFactory();
        Item item=factory.create(25);
        assert(item.getName().equals("Wooden Bow"));
    }

    @Test
    public void instantiateWeaponShouldWoodenCrossbow() {
        ItemFactory factory=new ItemFactory();
        Item item=factory.create(26);
        assert(item.getName().equals("Wooden Crossbow"));
    }

    @Test
    public void instantiateArmorShouldReturnMediumArmor() {
        ItemFactory factory=new ItemFactory();
        Item item=factory.create(19);
        assert(item.getName().equals("Medium armor"));
    }
}

