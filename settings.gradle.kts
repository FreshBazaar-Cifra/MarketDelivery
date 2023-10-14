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

rootProject.name = "MarketDelivery"
include(":app")
include(":core:model")
include(":core:designsystem")
include(":feature:marketselect")
include(":core:data")
include(":core:network")
include(":core:common")
include(":core:datastore")
include(":feature:onboarding")
include(":feature:placeselect")
include(":feature:buyorder")
include(":feature:basket")
include(":feature:productdetails")
include(":feature:profile")
include(":feature:productslist")
include(":feature:logincontinue")
include(":feature:profilesettings")
include(":feature:orders")
include(":feature:orderdetails")
include(":feature:favorites")
include(":feature:login")
include(":feature:register")
include(":feature:addresschange")
include(":feature:splash")
