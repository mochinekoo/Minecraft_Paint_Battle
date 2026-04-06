package net.mochinekoserver.paint_battle.status;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum GameTeam {
    RED(ChatColor.RED, Material.RED_WOOL, "赤"),
    BLUE(ChatColor.BLUE, Material.BLUE_WOOL, "青");

    private final ChatColor color;
    private final String team_string;
    private final Material team_block;

    GameTeam(ChatColor color, Material team_block, String team_string) {
        this.color = color;
        this.team_string = team_string;
        this.team_block = team_block;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getTeamString() {
        return team_string;
    }

    public Material getTeamBlock() {
        return team_block;
    }
}