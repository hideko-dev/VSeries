package dev.hideko.vseries

import dev.hideko.vseries.base.VBase
import dev.hideko.vseries.base.VClass
import dev.hideko.vseries.message.VActionBar
import dev.hideko.vseries.message.VTitle
import dev.hideko.vseries.render.VParticle

class VSeries {

    companion object {

        fun VBase(): VBase {
            return VBase()
        }

        fun VClass(): VClass {
            return VClass()
        }

        fun VTitle(): VTitle {
            return VTitle()
        }

        fun VActionBar(): VActionBar {
            return VActionBar()
        }

        fun VParticle(): VParticle {
            return VParticle()
        }

    }

}
