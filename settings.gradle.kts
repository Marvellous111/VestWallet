pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "tbd-oss-thirdparty"
            url = uri("https://blockxyz.jfrog.io/artifactory/tbd-oss-thirdparty-maven2/")
            mavenContent {
                releasesOnly()
            }
        }
    }
}

rootProject.name = "VestWallet"
include(":app")
 