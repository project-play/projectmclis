

plugins {
    id("java")
}

group = "me.mclis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.21.7-R0.1-SNAPSHOT")

    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.test {
    useJUnitPlatform()
}

