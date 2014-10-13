package jackyoustra.titleplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

public class NametagListener implements Listener {
	@EventHandler(priority = EventPriority.NORMAL)
	public void onNametag(AsyncPlayerReceiveNameTagEvent event){ // happens to everyone in view of the player, not the player itself
		Player thePersonYouAreAnalyzing = event.getNamedPlayer();
		event.setTag( Main.nameBank.get(thePersonYouAreAnalyzing.getUniqueId()) + " " + thePersonYouAreAnalyzing.getName());
	}
}
