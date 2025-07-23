package me.mclis.main.Listeners;

import me.mclis.main.Inventories.TestInv;
import me.mclis.main.Inventories.TestInventory;
import me.mclis.main.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickInventory implements Listener {
    Main main;

    public ClickInventory(Main plugin){
    this.main = plugin;
    }



    @EventHandler
    public void onClick(InventoryClickEvent e){
        Inventory inventory = e.getInventory();


        if (!(inventory.getHolder(false) instanceof TestInventory ti)) {
            // It's not our inventory, ignore it.
            return;
        }else{

            ItemStack i1 = new ItemStack(Material.COMPASS);
            e.getWhoClicked().sendMessage(e.getCurrentItem().toString());
            if(e.getCurrentItem().equals(i1)){
                e.getWhoClicked().sendMessage(e.getCurrentItem().toString());
                TestInv ti1 = new TestInv(main);
                e.getWhoClicked().openInventory(ti1.getInventory());
                e.getWhoClicked().sendMessage("inv opened");
                e.setCancelled(true);

            }else{
                e.setCancelled(true);
            }

            e.setCancelled(true);
        }



    }


}
