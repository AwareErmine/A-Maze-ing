plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.10"
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src"))
        }
        resources {
            setSrcDirs(listOf("src/resources"))
        }
    }
}

application {
    mainClass.set("Main")
}

javafx {
    version = "17"
    modules = listOf("javafx.controls", "javafx.fxml")
}