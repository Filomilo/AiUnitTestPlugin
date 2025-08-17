plugins {
    kotlin("jvm") version "2.2.0"
}

group = "org.filomilo.AiTestGenerotorAnalisis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation(project(":AiTestGenerator"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}