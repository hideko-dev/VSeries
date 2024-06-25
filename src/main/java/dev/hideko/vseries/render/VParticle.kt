package dev.hideko.vseries.render

import org.bukkit.Bukkit
import org.bukkit.Location
import xyz.xenondevs.particle.ParticleBuilder
import xyz.xenondevs.particle.ParticleEffect
import xyz.xenondevs.particle.data.ParticleData
import java.awt.Color

class VParticle(
    private var location: Location? = null
) {

    private var particle: VParticleList? = null
    private var type: VParticleType = VParticleType.DOT
    private var particleData: ParticleData? = null
    private var color: Color = Color.RED
    private var amount: Int = 10
    private var speed: Double = 1.0

    fun setParticle(particle: VParticleList): VParticle {
        this.particle = particle
        return this
    }

    fun setType(type: VParticleType): VParticle {
        this.type = type
        return this
    }

    fun setColor(color: Color): VParticle {
        this.color = color
        return this
    }

    fun setAmount(amount: Int): VParticle {
        this.amount = amount
        return this
    }

    fun setSpeed(speed: Double): VParticle {
        this.speed = speed
        return this
    }

    fun setParticleData(particleData: ParticleData): VParticle {
        this.particleData = particleData
        return this
    }

    fun use() {

        val location = this.location ?: throw IllegalStateException()
        val particle = this.particle ?: throw IllegalStateException()

        val type = this.type
        val amount = this.amount
        val color = this.color
        val particleEffect = ParticleEffect.valueOf(particle.toString())
        val speed = this.speed.toFloat()
        val dataParticle = this.particleData

        when (type) {
            VParticleType.DOT -> {
                ParticleBuilder(particleEffect, location)
                    .setAmount(amount)
                    .setSpeed(speed)
                    .setColor(color)
                    .setParticleData(dataParticle)
                    .display()
            }
            VParticleType.BLOCK_OUTLINE -> {
                val world = location.world ?: return
                val vertices = arrayOf(
                    location.clone().add(0.0, 0.0, 0.0),
                    location.clone().add(1.0, 0.0, 0.0),
                    location.clone().add(0.0, 1.0, 0.0),
                    location.clone().add(1.0, 1.0, 0.0),
                    location.clone().add(0.0, 0.0, 1.0),
                    location.clone().add(1.0, 0.0, 1.0),
                    location.clone().add(0.0, 1.0, 1.0),
                    location.clone().add(1.0, 1.0, 1.0)
                )
                for (i in vertices.indices) {
                    for (j in i + 1 until vertices.size) {
                        val start = vertices[i]
                        val end = vertices[j]
                        if (start.distance(end) == 1.0) {
                            val steps = 10
                            val stepX = (end.x - start.x) / steps
                            val stepY = (end.y - start.y) / steps
                            val stepZ = (end.z - start.z) / steps
                            for (i in 0..steps) {
                                val x = start.x + stepX * i
                                val y = start.y + stepY * i
                                val z = start.z + stepZ * i
                                ParticleBuilder(particleEffect, Location(Bukkit.getWorld("world"), x, y, z))
                                    .setAmount(amount)
                                    .setColor(color)
                                    .setSpeed(speed)
                                    .setParticleData(dataParticle)
                                    .display()
                            }
                        }
                    }
                }
            }
        }

    }

}