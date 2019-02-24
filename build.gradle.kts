import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("maven")
    kotlin("jvm") version "1.3.21"
}

group = "me.camdenorrb"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("me.camdenorrb:KCommons:+")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    //implementation("com.sxtanna.korm:Korm:+")?
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}