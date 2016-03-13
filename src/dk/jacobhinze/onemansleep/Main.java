package dk.jacobhinze.onemansleep;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener{
	
	
	public void onEnable(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(this, this);
	}
	@EventHandler
	public void onPlayerSleep (PlayerBedEnterEvent e){
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		World world = loc.getWorld();
		if(p.hasPermission("OneManSleep.use")){
			new BukkitRunnable(){
				private int countdown = 2;
				@Override
				public void run(){
						countdown--;
						
						if(countdown == 0){
							cancel();
							world.setTime(0);
							e.setCancelled(true);
						}
					}
				}.runTaskTimer(this, 20L, 20L);
		}
	}

}
