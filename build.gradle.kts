import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0-alpha4-build362"
}

group = "de.amirrocker"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)

    implementation(kotlin("stdlib-jdk8"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")

    implementation("com.squareup:kotlinpoet:1.10.2")

    val retrofit_version = "2.9.0"

    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:retrofit-mock:$retrofit_version")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

//    implementation("org.jetbrains.kotlinx:kotlinx-html:0.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.4.32")

//    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

}


//test {
//    testLogging {}
//
//    useJUnitPlatform {}
//}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

// https://www.cornerinthemiddle.com/software%20engineering/using-gradle-kotlin-dsl-with-junit5/
tasks {
    test {
        useJUnitPlatform()
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "OperationComposeDesktop"
            packageVersion = "1.0.0"
        }
    }
}

