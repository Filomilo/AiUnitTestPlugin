plugins {
    kotlin("jvm") version "2.2.0"
}

group = "org.filomilo.AiTestGenerotorAnalisis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(
        url  ="https://central.sonatype.com/repository/maven-snapshots/"
    )
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation(project(":AiTestGenerator"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("io.github.pdvrieze.xmlutil:core:0.91.2")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.91.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.19.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.13.4")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}