
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.3.1"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
}

group = "com.ApiStream"
version = "0.0.1"

application {
    mainClass.set("com.ApiStream.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.apache.kafka:kafka-clients:2.8.0") // Adicione esta linha para o Kafka
    implementation("software.amazon.awssdk:dynamodb:2.17.52") // Adicione esta linha para o AWS SDK
    implementation("io.github.resilience4j:resilience4j-kotlin:1.7.0")
    implementation("com.google.code.gson:gson:2.8.6")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    
}

