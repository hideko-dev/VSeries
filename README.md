<h1 align="center">VSeries</h1>

<p align="center">
  <b>A spigot library supporting various features from <code>1.8</code> to <code>1.19.3</code></b><br><br>

  <a href="https://github.com/Hideko-Dev/VSeries/issues">
    <img src="https://img.shields.io/github/issues/Hideko-Dev/VSeries" alt="issues"/>
  </a>
  <a href="https://github.com/Hideko-Dev/VSeries/stargazers">
    <img src="https://img.shields.io/github/stars/Hideko-Dev/VSeries" alt="stars"/>
  </a>
  <a href="https://github.com/Hideko-Dev/VSeries/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/Hideko-Dev/VSeries" alt="license"/>
  </a>
<br><br>
  <a href="#features">Features</a> •
  <a href="#usage">Usage</a> •
  <a href="#download">Download</a>
</p>



# Features

- Compatible with versions from <code>1.8</code> to <code>1.19.3</code>
- Title with fade and stay settings at <code>1.8</code>
- Action bar at <code>1.8</code>
- Particles at <code>1.8</code>
- Conveniently classified <sup>(Java and Kotlin)</sup>



# Download

<p style="display: flex; align-items: center; gap: 5px">The latest version <img src="https://img.shields.io/github/v/release/ByteZ1337/ParticleLib"/> can be downloaded on the <a href="https://github.com/hideko-dev/VSeries/releases">releases</a> page.</p>

### Maven

```xml
<!-- Repository -->
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```
```xml
<!-- Dependency -->
<dependency>
    <groupId>com.github.Hideko-Dev</groupId>
    <artifactId>VSeries</artifactId>
    <version>VERSION</version>
</dependency>
```

### Gradle

```groovy
// Repository
repositories {
  mavenCentral()
  maven { url 'https://jitpack.io' }
}
```
```groovy 
// Dependency
dependencies {
    implementation 'com.github.Hideko-Dev:VSeries:-SNAPSHOT'
}
```

> [!NOTE]  
> VSeries is available on Jitpack. If you want to use the development version, use the <a href="https://github.com/hideko-dev/VSeries/tree/dev/">dev branch</a>.

# Usage

## VParticle
**Class:** ```VParticle```, ```VParticleList```, ```VParticleType```

### Simple

This simply makes particles appear at a specified location.
<br>
The ``Type`` at this time is ``VParticleType.DOT``

```kotlin
VParticle(location)
  .setType(VParticleType.DOT)
  .setParticle(VParticleList.CLOUD)
  .use()
```

---

### Delayed and Amount

This allows you to specify the amount of particles to appear and the time after they appear.

<b>Amount</b>
```kotlin
VParticle(location)
  .setType(VParticleType.DOT)
  .setAmount(100) // Default is 10
  .setParticle(VParticleList.CRIT)
  .use()
```

<b>Speed</b>
```kotlin
VParticle(location)
  .setType(VParticleType.DOT)
  .setSpeed(0.5) // Default is 1.0
  .setParticle(VParticleList.CRIT)
  .use()
```

---

### Block shape

This allows particles in the shape of blocks to appear.
The ``Type`` at this time is ``VParticleType.BLOCK_OUTLINE``

```kotlin
VParticle(location)
  .setType(VParticleType.BLOCK_OUTLINE)
  .setParticle(VParticleList.REDSTONE)
  .setColor(Color.CYAN)
  .use()
```

<small>If you use the above code, your particles will look like this</small>

<img src="https://i.gyazo.com/8bfc3a8e5f3f877279a404107c864b7e.png" alt="Image of block shape particle">


---

### Colored dust

This causes colored redstone dust particles to appear.
<br>
Color is specified using ``java.awt``

```kotlin
VParticle(location)
  .setParticle(VParticleList.REDSTONE)
  .setColor(Color.RED)
  .use()
```

---

### Textured Material

These are particles that can appear as textures on blocks and items.
<br/>
ItemCrack, FallingDust can be used.

<b>Block Texture</b>

```kotlin
VParticle(location)
    .setParticle(VParticleList.BLOCK_FALLING)
    .setParticleData(BlockTexture(Material.STONE))
    .display()
```

<b>Item Texture</b>

```kotlin
val item = ItemStack(Material.DIAMOND_AXE)
VParticle(location)
    .setParticle(VParticleList.ITEM_CRACK)
    .setParticleData(ItemTexture(item))
    .display()
```

---

## VActionBar
**Class:** ```VActionBar```

With the following simple code, you can easily send an action bar to a specified player.

### Send
```kotlin
VActionBar.send(player, "Hello World")
```

### Clear
```kotlin
VActionBar.clear(player)
```

---

## VTitle
**Class:** ```VTitle```

With the following simple code, you can easily send titles with ``fade-in``, ``fade-out``, and ``stay`` to your players.
### Send
The following code is a ``30-tick`` fade-in, ``10-tick stay``, and ``30-tick fade-out`` court
```kotlin
VTitle.send(player, 30, 10, 30, "", "")
```
### Clear

```kotlin
VTitle.send(player, "Hello World")
```

---

# VBase

**Class:** ```VBase```

This is a simplified version of ``registerEvents() in PluginManager`` of ``org.bukkit.JavaPlugin``
<br>
and ``getCommand()`` of ``org.bukkit.Server`` It can be as simple as the following code.

```kotlin
class TestPlugin : JavaPlugin() {
    override fun onEnable() {
        
        VBase()
            .newEvents(this, listOf(
                TestListener()
            ))
            .newCommands(this, listOf(
                "testCommand" to TestCommandExecutor()
            ))
        
    }
}
```

---

# VClass

**Class:** ```VClass```

**This is for advanced users.**

This code forces the class of ``net.minecraft.server`` to be retrieved.

```kotlin
VClass.getNMSClass("PacketPlayOutTitle")
```

This code sends packets to the specified player.

```kotlin
VClass.sendPacket(player, Packet)
```

---

> [!TIP]
> The "V" in VSeries is inspired by the "V" in "Version," which stands for more than one version.

---

This VSeries was created by <a href="https://hideko-dev.com">Hideko</a>.
<br>
Part of the ``ParticleLib`` by <a href="https://github.com/ByteZ1337">ByteZ1137</a> code is used in VParticle.
