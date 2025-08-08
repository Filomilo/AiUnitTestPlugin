pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

}

//plugins {
//    id("com.gradle.enterprise")
//}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
    }
}
rootProject.name = "AiUnitTestPlugin"