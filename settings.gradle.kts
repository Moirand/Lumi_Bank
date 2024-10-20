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
        maven { url = uri("https://www.jitpack.io" ) }
    }
}

rootProject.name = "Lumi Bank"
include(":app")
include(":core")
include(":data")
include(":di")
include(":domain")
include(":common")
include(":feature_auth")
include(":feature_dashboard")
include(":feature_mutation")
include(":feature_transfer")
