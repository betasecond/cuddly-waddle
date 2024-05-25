plugins {
    kotlin("jvm")
}

group = "edu.jimei"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // 直接声明 jackson-databind 的版本
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation(project(":ics.struct"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.test {
    useJUnitPlatform()
}
