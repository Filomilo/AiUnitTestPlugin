rootProject.name = "AiUnitTestPlugin"

include(":AiTestGenerator")
include(":AiTestGenerotorAnalisis")
plugins {
    id("com.gradle.develocity") version "3.17.5"
}
develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/help/legal-terms-of-use"
        termsOfUseAgree = "yes"

        // publishing.onlyIf { false }
        // If you don't need to auto-publish the build scan, uncomment it.

    }
}