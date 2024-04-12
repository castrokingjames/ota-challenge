import java.net.URI

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

rootProject.name = "ota-challenge"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":challenge")
include(":timber")
include(":ui:common")
include(":ui:compose")
include(":ui:viewmodel")
include(":ui:glide")
include(":di:annotation")
include(":model")
include(":domain")
include(":data")
include(":datasource:remote")
include(":datasource:local")
include(":feature:home")
include(":feature:details")