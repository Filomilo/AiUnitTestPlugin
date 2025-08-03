val kotlinVersion = "2.2.0" // updated

plugins {
    id("org.jetbrains.kotlin.jvm") version "2.2.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0"
    id("org.jetbrains.intellij") version "1.17.3"

    id("java")
}

group = "org.filomilo.AiUnitTestPlugin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.docker-java:docker-java:3.5.3")
    implementation("com.github.docker-java:docker-java-transport-httpclient5:3.5.3")
    testImplementation("org.slf4j:slf4j-simple:2.0.17")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.5")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.9.0")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    runtimeOnly("com.squareup.okio:okio-jvm:3.15.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.0")
//    implementation("com.squareup.okhttp3:okhttp:5.0.0")
}
// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.2.6")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}


sourceSets {
    test {
        java.srcDirs("src/test/java")
        kotlin.srcDirs("src/test/kotlin")
    }
}


tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
    test {
        useJUnitPlatform()
    }
}
kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // or 21, but must match both sides
    }
}
