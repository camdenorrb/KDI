import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("maven-publish")
    kotlin("jvm") version "1.3.40"
}

group = "me.camdenorrb"
version = "1.0.1"

repositories {

    mavenLocal()
    mavenCentral()

    maven("https://jitpack.io")
}

dependencies {

    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.github.camdenorrb:KCommons:V1.1.0")
    //implementation("com.sxtanna.korm:Korm:+")?
}

tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs = listOf("-Xuse-experimental=kotlin.ExperimentalStdlibApi")
    kotlinOptions.jvmTarget = "1.8"
}
