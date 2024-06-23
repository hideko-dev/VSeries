package dev.hideko.vseries.message

import dev.hideko.vseries.base.VClass
import dev.hideko.vseries.base.VClass.Companion.getNMSClass
import dev.hideko.vseries.base.VClass.Companion.sendPacket
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.lang.reflect.Constructor

class VTitle {

    companion object {

        fun send(player: Player, fadeIn: Int, stay: Int, fadeOut: Int, title: String?, subtitle: String?) {

            var title = title
            var subtitle = subtitle

            try {
                var e: Any
                var chatTitle: Any
                var chatSubtitle: Any
                var subtitleConstructor: Constructor<*>
                var titlePacket: Any
                var subtitlePacket: Any

                if (title != null) {
                    title = ChatColor.translateAlternateColorCodes('&', title)
                    title = title.replace("%player%".toRegex(), player.displayName)
                    e = getNMSClass("PacketPlayOutTitle")!!.declaredClasses[0].getField("TIMES").get(null)
                    chatTitle = getNMSClass("IChatBaseComponent")!!.declaredClasses[0].getMethod(
                        "a", String::class.java
                    ).invoke(null, "{\"text\":\"$title\"}")
                    subtitleConstructor = getNMSClass("PacketPlayOutTitle")!!.getConstructor(
                        getNMSClass("PacketPlayOutTitle")!!.declaredClasses[0],
                        getNMSClass("IChatBaseComponent"),
                        Int::class.javaPrimitiveType,
                        Int::class.javaPrimitiveType,
                        Int::class.javaPrimitiveType
                    )
                    titlePacket = subtitleConstructor.newInstance(e, chatTitle, fadeIn, stay, fadeOut)
                    VClass.sendPacket(player, titlePacket)
                    e = getNMSClass("PacketPlayOutTitle")!!.declaredClasses[0].getField("TITLE").get(null)
                    chatTitle = getNMSClass("IChatBaseComponent")!!.declaredClasses[0].getMethod(
                        "a", String::class.java
                    ).invoke(null, "{\"text\":\"$title\"}")
                    subtitleConstructor = getNMSClass("PacketPlayOutTitle")!!.getConstructor(
                        getNMSClass("PacketPlayOutTitle")!!.declaredClasses[0],
                        getNMSClass("IChatBaseComponent")
                    )
                    titlePacket = subtitleConstructor.newInstance(e, chatTitle)
                    sendPacket(player, titlePacket)
                }

                if (subtitle != null) {
                    subtitle = ChatColor.translateAlternateColorCodes('&', subtitle)
                    subtitle = subtitle.replace("%player%".toRegex(), player.displayName)
                    e = getNMSClass("PacketPlayOutTitle")!!.declaredClasses[0].getField("TIMES").get(null)
                    chatSubtitle = getNMSClass("IChatBaseComponent")!!.declaredClasses[0].getMethod(
                        "a", String::class.java
                    ).invoke(null, "{\"text\":\"$subtitle\"}")
                    subtitleConstructor = getNMSClass("PacketPlayOutTitle")!!.getConstructor(
                        getNMSClass("PacketPlayOutTitle")!!.declaredClasses[0],
                        getNMSClass("IChatBaseComponent"),
                        Int::class.javaPrimitiveType,
                        Int::class.javaPrimitiveType,
                        Int::class.javaPrimitiveType
                    )
                    subtitlePacket = subtitleConstructor.newInstance(e, chatSubtitle, fadeIn, stay, fadeOut)
                    sendPacket(player, subtitlePacket)

                    e = getNMSClass("PacketPlayOutTitle")!!.declaredClasses[0].getField("SUBTITLE").get(null)
                    chatSubtitle = getNMSClass("IChatBaseComponent")!!.declaredClasses[0].getMethod(
                        "a", String::class.java
                    ).invoke(null, "{\"text\":\"$subtitle\"}")
                    subtitleConstructor = getNMSClass("PacketPlayOutTitle")!!.getConstructor(
                        getNMSClass("PacketPlayOutTitle")!!.declaredClasses[0],
                        getNMSClass("IChatBaseComponent"),
                        Int::class.javaPrimitiveType,
                        Int::class.javaPrimitiveType,
                        Int::class.javaPrimitiveType
                    )
                    subtitlePacket = subtitleConstructor.newInstance(e, chatSubtitle, fadeIn, stay, fadeOut)
                    sendPacket(player, subtitlePacket)
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }

        fun clear(player: Player) {
            send(player, 0, 0, 0, "", "")
        }

    }

}