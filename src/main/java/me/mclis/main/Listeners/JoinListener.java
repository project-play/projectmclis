package me.mclis.main.Listeners;

import me.mclis.main.API.MySQLAPI;
import me.mclis.main.Util.MySQLFileManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        String name = e.getPlayer().getName();
        e.joinMessage(Component.text("+ " + name, NamedTextColor.GREEN));
        MySQLAPI mapi = new MySQLAPI();
        try {
            mapi.isUserregistered(e.getPlayer());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
