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
    }
}

rootProject.name = "Lumi Bank"
include(":app")
include(":common")
include(":feature_auth")
include(":feature_auth:domain")
include(":feature_auth:data")
include(":feature_auth:di")
include(":feature_dashboard")
include(":feature_dashboard:domain")
include(":feature_dashboard:data")
include(":feature_dashboard:di")
