package me.mclis.main.Inventories;

import me.mclis.main.Main;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TestInventory implements InventoryHolder {

    private final Inventory inv;

    public TestInventory(Main plugin) {
        this.inv = plugin.getServer().createInventory(this, 9);
        fillInv();
    }


    public void fillInv(){
        ItemStack i1 = new ItemStack(Material.COMPASS);
        ItemStack i2 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack i3 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack i4 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack i5 = new ItemStack(Material.CRAFTING_TABLE);
        ItemStack i6 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack i7 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack i8 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack i9 = new ItemStack(Material.BARRIER);
        inv.setItem(0,i1);
        inv.setItem(1,i2);
        inv.setItem(2,i3);
        inv.setItem(3,i4);
        inv.setItem(4,i5);
        inv.setItem(5,i6);
        inv.setItem(6,i7);
        inv.setItem(7,i8);
        inv.setItem(8,i9);


    }




    @Override
    public @NotNull Inventory getInventory() {
        return this.inv;
    }




}
