package jackyoustra.titleplugin;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event){
		Player logee = event.getPlayer();
		UUID playerId = logee.getUniqueId();
		if(!Main.nameBank.containsKey(playerId)) Main.nameBank.put(playerId, "NOOB:"); // do to set initial value
	}	
}
