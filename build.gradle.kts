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

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.stolengalaxy.sudoku_solver.Main"
    }
}
