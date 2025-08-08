pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

}
gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
    }
}
rootProject.name = "AiUnitTestPlugin"