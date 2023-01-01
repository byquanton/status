dependencyResolutionManagement{
    versionCatalogs{
        create("libs"){
            version("cloud", "1.8.0")

            // Paper
            plugin("paper-run","xyz.jpenilla.run-paper").version("2.0.1")
            plugin("paper-userdev","io.papermc.paperweight.userdev").version("1.3.8")
            plugin("plugin-yml","net.minecrell.plugin-yml.bukkit").version("0.5.2")

            version("paper-api","1.19.3-R0.1-SNAPSHOT")

            library("cloud-paper","cloud.commandframework","cloud-paper").versionRef("cloud")
            library("cloud-minecraft-extras","cloud.commandframework","cloud-minecraft-extras").versionRef("cloud")
        }
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "status"