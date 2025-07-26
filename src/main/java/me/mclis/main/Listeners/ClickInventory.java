package me.mclis.main.Listeners;

import me.mclis.main.API.PteroAPI;
import me.mclis.main.Inventories.CVanillaInv;
import me.mclis.main.Inventories.CreateServerInv;
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
    Main main = Main.getInstance();

    public ClickInventory(Main plugin){
    this.main = plugin;
    }



    @EventHandler
    public void onClick(InventoryClickEvent e){
        Inventory inventory = e.getInventory();


        if (inventory.getHolder(false) instanceof TestInventory ti) {
            e.setCancelled(true);
            System.out.println("Debug hat geklappt bitte gucken wie das geklappt hat");
            ItemStack i1 = new ItemStack(Material.CRAFTING_TABLE);
            e.getWhoClicked().sendMessage(e.getCurrentItem().toString());
            if(e.getCurrentItem().equals(i1)){
                e.getWhoClicked().sendMessage(e.getCurrentItem().toString());
                CreateServerInv ti1 = new CreateServerInv(main);
                e.getWhoClicked().openInventory(ti1.getInventory());
                e.getWhoClicked().sendMessage("inv opened");
                e.setCancelled(true);

            }else{
                e.setCancelled(true);
            }

        }else if(inventory.getHolder(false) instanceof CreateServerInv csi){
            e.setCancelled(true);
            ItemStack gras = new ItemStack(Material.GRASS_BLOCK);
            ItemStack anvil = new ItemStack(Material.ANVIL);
            if(e.getCurrentItem().equals(gras)){
                CVanillaInv cv = new CVanillaInv(main);
                e.getWhoClicked().openInventory(cv.getInventory());
            }else if(e.getCurrentItem().equals(anvil)){
                e.getWhoClicked().sendMessage("Noch nicht!");
            }else {
                return;
            }
        }else if(inventory.getHolder(false) instanceof CVanillaInv cvi){
            PteroAPI papi = new PteroAPI();
                e.setCancelled(true);
            ItemStack gras = new ItemStack(Material.GRASS_BLOCK);
            ItemStack anvil = new ItemStack(Material.OAK_PLANKS);
            if(e.getCurrentItem().equals(gras)){
                papi.createServerVanilla("testertest", 1, 3, "ghcr.io/pterodactyl/yolks:java_21", 12480, 10000, 0, 2, 1, 2, 6, "latest");
            }else if(e.getCurrentItem().equals(anvil)){
                papi.createServerVanilla("testertester", 1, 3, "ghcr.io/pterodactyl/yolks:java_21", 12480, 10000, 0, 2, 1, 2, 7, "1.21.7");
            }else {
                return;
            }



        }


        else{



            e.setCancelled(false);
            return;
        }



    }


}
