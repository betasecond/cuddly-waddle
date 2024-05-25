import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "edu.jimei"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("io.github.epicarchitect:calendar-compose-basis-desktop:1.0.5")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    // 添加 ProGuard 依赖
    runtimeOnly("com.guardsquare:proguard-gradle:7.4.2")
    implementation(project(":ics.struct"))
    implementation(project(":json.parser"))
    implementation(project(":ics.generator"))
}



compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "coursapp-kt"
            packageVersion = "1.0.0"
        }
    }
}
