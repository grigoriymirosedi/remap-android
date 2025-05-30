pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ReMap"
include(":app")
include(":features:home")
include(":features:map")
include(":core")
include(":data:recyclepoint")
include(":data:events")
include(":data:profile")
include(":data:community")
include(":core:data")
include(":core:ui")
include(":features:events")
include(":features:profile")
include(":features:auth")
include(":data:auth")
