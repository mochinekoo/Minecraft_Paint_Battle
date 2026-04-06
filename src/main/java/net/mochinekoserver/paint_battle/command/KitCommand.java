package net.mochinekoserver.paint_battle.command;

import net.mochinekoserver.paint_battle.manager.KitManager;
import net.mochinekoserver.paint_battle.status.KitType;
import net.mochinekoserver.paint_battle.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("kit_select")) {
            Player player = Bukkit.getPlayer(args[0]);
            KitManager.getInstance().setKit(player.getUniqueId(), KitType.valueOf(args[1]));
            ChatUtil.sendInfoMessage(player, "キットが選択されました。");
        }
        return false;
    }

}
