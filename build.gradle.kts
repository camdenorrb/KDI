import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("maven")
    kotlin("jvm") version "1.3.10"
}

group = "me.camdenorrb"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}