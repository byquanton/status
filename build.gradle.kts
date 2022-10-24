import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

group = "me.byquanton.plugins"
version = "1.0.1"

plugins {
    `java-library`
    alias(libs.plugins.paper.run)
    alias(libs.plugins.paper.userdev)
    alias(libs.plugins.plugin.yml)
    alias(libs.plugins.shadow)
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://hub.jeff-media.com/nexus/repository/jeff-media-public/")
}

dependencies {
    paperDevBundle(libs.versions.paper.api.get())
    bukkitLibrary(libs.cloud.paper)
    bukkitLibrary(libs.cloud.minecraft.extras)
}

tasks{
    assemble {
        dependsOn(reobfJar)
    }
}

bukkit {
    main = "me.byquanton.plugins.status.StatusPlugin"
    apiVersion = "1.19"
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    authors = listOf("byquanton")
    softDepend = listOf("floodgate")
}