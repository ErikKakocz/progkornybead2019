package hu.inf.unideb.test;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;

import hu.inf.unideb.Items.Inventory;
import hu.inf.unideb.Items.Item;
import hu.inf.unideb.Items.TooMuchItemsException;
import hu.inf.unideb.Items.Weapon;
import hu.inf.unideb.Items.WeaponType;
import hu.inf.unideb.itemOperations.ItemTinkering;

public class ItemTinkeringTest {

    @Test
    public void getCraftablesShouldReturncraftables() {
        ItemTinkering tinkerer=new ItemTinkering();
        Logger log=Logger.getLogger(this.getClass());
        Inventory inv=new Inventory();
        try {
            inv.addItem(new Item(1,1.5,100,"stick"));
        } catch (TooMuchItemsException e) {
            log.error("Too much items in test");
        }
        assert(tinkerer.getCraftableItems(inv).equals("id:2. stick->handle\n"));
    }

    @Test
    public void getCraftablesShouldReturncraftablesWithMoreComponents() {
        ItemTinkering tinkerer=new ItemTinkering();
        Logger log=Logger.getLogger(this.getClass());
        Inventory inv=new Inventory();
        try {
            inv.addItem(new Item(2,1.5,100,"handle"));
            inv.addItem(new Item(1,1.5,100,"stick"));
        } catch (TooMuchItemsException e) {
            log.error("Too much items in test");
        }
        assert(tinkerer.getCraftableItems(inv)
                .equals("id:2. stick->handle\n"
                        + "id:3. stick+handle->bow handle\n"));
    }
    
    @Test
    public void craftItemShouldReturnCrossbow() {
        Inventory inv=new Inventory();
        Logger log=Logger.getLogger(this.getClass());
        try {
            inv.addItem(new Item(14,0.5,100,"String"));
            inv.addItem(new Item(15,4.5,100,"Crossbow Handle"));
        } catch (TooMuchItemsException e) {
            log.error("Too much items in test");
        }
        ItemTinkering tinkerer=new ItemTinkering();
        inv=tinkerer.craftItem(26, inv);
        log.error(inv.getBackpackItems().size());
        Weapon weapon=(Weapon)inv.getBackpackItems().get(0);
        assert(weapon.getName().equals("Wooden Crossbow"));
        
    }
    
    @Test
    public void repairItemShouldRepairItem() {
        ItemTinkering tinkerer=new ItemTinkering();
        Weapon weapon=new Weapon(26,10,30,"Wooden CrossBow",
                50,15,WeaponType.CROSSBOW);
        weapon=(Weapon)tinkerer.repairItem(weapon);
        assertEquals(weapon.getDurability(),100);
        assert(weapon.getAttack()==50);
    }
}
