package me.mclis.main.Commands;

import me.mclis.main.API.PteroAPI;
import me.mclis.main.Inventories.TestInventory;
import me.mclis.main.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class testcmd implements CommandExecutor {

    Main plugin;

    public testcmd(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String arg, @NotNull String @NotNull [] args) {

        if(cmd.getName().equals("test")){

            if(sender.isOp()){
                if(sender instanceof Player){
                    PteroAPI papi = new PteroAPI();
                    Player p = (Player) sender;
                    p.sendMessage("Test was successfull");
                    ItemStack is = new ItemStack(Material.ACACIA_LOG);
                    ItemMeta im = is.getItemMeta();
                    im.addEnchant(Enchantment.SHARPNESS, 200, true);
                    is.setItemMeta(im);

                    p.getInventory().setItemInOffHand(is);


                    TestInventory ti = new TestInventory(plugin);

                    p.openInventory(ti.getInventory());
                    papi.createServerVanilla("tester", 1, 3, "ghcr.io/pterodactyl/yolks:java_21", 12480, 10000, 0, 2, 1, 2, 5, "latest");






                }else{
                    sender.sendMessage("Test was successful");
                }
            }else{
                sender.sendMessage("You can't use that command");
                return true;
            }


            return true;
        }




        return false;
    }
}
