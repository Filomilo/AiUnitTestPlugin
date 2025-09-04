import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.2.0"
    java
    application
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.20-RC"
}

application {
    mainClass.set("org.filomilo.AiTestGenerotorAnalisis.Main")
}
group = "org.filomilo.AiTestGenerotorAnalisis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(
        url  ="https://central.sonatype.com/repository/maven-snapshots/"
    )
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/grazie/maven")
    }

}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.slf4j:slf4j-api:2.0.17")
    testImplementation("org.slf4j:slf4j-simple:2.0.17")
    implementation("ch.qos.logback:logback-classic:1.5.18")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation(project(":AiTestGenerator"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")

    implementation("io.github.pdvrieze.xmlutil:core:0.91.2")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.91.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.19.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.13.4")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.20.0-rc1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}


        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            compilerOptions {
                freeCompilerArgs.add("-Xannotation-default-target=param-property")
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
