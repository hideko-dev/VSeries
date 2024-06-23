package dev.hideko.vseries.base

import org.bukkit.Server
import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class VBase {

    fun newEvents(plugin: JavaPlugin, events: List<Listener>): VBase {
        events.forEach { event ->
            plugin.server.pluginManager.registerEvents(event, plugin)
        }
        return this
    }

    fun newCommands(plugin: JavaPlugin, commands: List<Pair<String, CommandExecutor>>): VBase {
        commands.forEach { (name, executor) ->
            plugin.getCommand(name)?.setExecutor(executor)
        }
        return this
    }
}