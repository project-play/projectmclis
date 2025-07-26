package me.mclis.main;

import me.mclis.main.API.PteroAPI;
import me.mclis.main.Commands.testcmd;
import me.mclis.main.Listeners.ClickInventory;
import me.mclis.main.Listeners.JoinListener;
import me.mclis.main.Util.FileManager;
import me.mclis.main.Util.MySQLFileManager;
import org.apache.logging.log4j.CloseableThreadContext;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public class Main extends JavaPlugin {



    private static Main instance;
    public Main(){
        instance = this;
    }

    public static Main getInstance(){
        return instance;
    }

    @Override
    public void onLoad() {
        getLogger().info("Plugin is starting");
    }

    @Override
    public void onEnable() {
        registerListener();
        registerCommands();

    //    papi.createServerVanilla("tester", 1, 3, "ghcr.io/pterodactyl/yolks:java_21", 12480, 10000, 0, 2, 1, 2, 5, "latest");

        FileManager config = new FileManager("config.yml", getDataFolder());

        String prefix = config.toFileConfigurtaion().getString("Util.prefix");
        MySQLFileManager mysql = new MySQLFileManager("mysql.yml", getDataFolder());

        getLogger().info(prefix + "Plugin is started");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin is Disabled");
    }


    public void registerListener(){
        JoinListener jl = new JoinListener();
        getServer().getPluginManager().registerEvents(jl, this);
        getServer().getPluginManager().registerEvents(new ClickInventory(this), this);
    }

    public void registerCommands(){

        getCommand("test").setExecutor(new testcmd(this));
    }




}
