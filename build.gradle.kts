plugins {
    id("java")
}

group = "com.stolengalaxy"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
}

tasks.test {
    useJUnitPlatform()
}
