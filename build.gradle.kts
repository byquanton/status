import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

group = "me.byquanton.plugins"
version = "1.1.0"

plugins {
    `java-library`
    alias(libs.plugins.paper.run)
    alias(libs.plugins.paper.userdev)
    alias(libs.plugins.plugin.yml)
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    paperDevBundle(libs.versions.paper.api.get())
    bukkitLibrary(libs.cloud.paper)
    bukkitLibrary(libs.cloud.minecraft.extras)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
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