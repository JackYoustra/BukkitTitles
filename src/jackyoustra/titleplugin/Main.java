/*
Copyright Jack Youstra. All rights reserved.
@author Jack Youstra

 */
package jackyoustra.titleplugin;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.kitteh.tag.TagAPI;

public class Main extends JavaPlugin{
	public static ConcurrentHashMap<UUID, String> nameBank;
	public final String NAMEBANKPATH = "names.bin";

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		String commandName = command.getName();
		switch (commandName.toLowerCase()) {
		case "giveprefix":
			if (checkArgLength(sender, args, 2)) {
				String targetName = args[0];
				for (Player player : getServer().getOnlinePlayers()) {
					if (player.getName().equals(targetName)) {
						nameBank.put(player.getUniqueId(), args[1]);
						TagAPI.refreshPlayer(player);
						return true;
					}
				}
				sender.sendMessage("Unable to find player");
				return true;
			}
			return false;
		case "dump":
			if (checkArgLength(sender, args, 0)) {
				for (Player player : getServer().getOnlinePlayers()) {
					String s = nameBank.get(player.getUniqueId()) + " : "
							+ player.getName();
					sender.sendMessage(s);
				}
				return true;
			}
			return false;
		default:
			break;
		}

		return super.onCommand(sender, command, label, args);
	}

	@Override
	public void onEnable() {
		try {
			nameBank = SLAPI.load(NAMEBANKPATH);
		} catch (Exception e) {
			nameBank = new ConcurrentHashMap<>();
			e.printStackTrace();
		}

		getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new NametagListener(), this);
		super.onEnable();
	}

	@Override
	public void onDisable() {
		try {
			SLAPI.save(nameBank, NAMEBANKPATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		super.onDisable();
	}

	public boolean checkArgLength(CommandSender sender, String[] args,
			int length) {
		if (length > args.length) {
			sender.sendMessage("too few arguments");
			return false;
		} else if (length < args.length) {
			sender.sendMessage("too many arguments");
			return false;
		}
		return true;
	}

}
