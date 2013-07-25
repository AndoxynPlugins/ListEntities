/*
 * Copyright (C) 2013 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bukkitdev.entitylist;

import java.util.EnumMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author daboross
 */
public class EntityList extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
	}

	@Override
	public void onDisable() {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("entitylist")) {
			for (World world : Bukkit.getWorlds()) {
				Map<EntityType, Integer> numberMap = new EnumMap<EntityType, Integer>(EntityType.class);
				for (Entity entity : world.getEntities()) {
					EntityType type = entity.getType();
					if (numberMap.containsKey(type)) {
						numberMap.put(type, numberMap.get(type) + 1);
					} else {
						numberMap.put(type, 1);
					}
				}
				sender.sendMessage(" -- " + world.getName() + " -- ");
				for (Map.Entry<EntityType, Integer> entry : numberMap.entrySet()) {
					sender.sendMessage(entry.getKey().getName() + ": " + entry.getValue());
				}
			}
		} else {
			sender.sendMessage("EntityList doesn't know about the command /" + cmd);
		}
		return true;
	}
}
