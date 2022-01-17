import org.gradle.internal.os.OperatingSystem

val lwjglVersion = "3.3.0"
var lwjglNatives = ""

if (OperatingSystem.current() == OperatingSystem.LINUX)
    lwjglNatives = "natives-linux"
else if (OperatingSystem.current() == OperatingSystem.MAC_OS)
    lwjglNatives = "natives-macos"
else if (OperatingSystem.current() == OperatingSystem.WINDOWS)
    lwjglNatives = "natives-windows"

plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    implementation ("org.lwjgl:lwjgl")
    implementation ("org.lwjgl:lwjgl-assimp")
    implementation ("org.lwjgl:lwjgl-glfw")
    implementation ("org.lwjgl:lwjgl-openal")
    implementation ("org.lwjgl:lwjgl-opengl")
    implementation ("org.lwjgl:lwjgl-stb")
    runtimeOnly ("org.lwjgl:lwjgl::$lwjglNatives")
    runtimeOnly ("org.lwjgl:lwjgl-assimp::$lwjglNatives")
    runtimeOnly ("org.lwjgl:lwjgl-glfw::$lwjglNatives")
    runtimeOnly ("org.lwjgl:lwjgl-openal::$lwjglNatives")
    runtimeOnly ("org.lwjgl:lwjgl-opengl::$lwjglNatives")
    runtimeOnly ("org.lwjgl:lwjgl-stb::$lwjglNatives")
}
sourceSets {
    main {
        java {
            setSrcDirs(listOf("src"))
        }
    }

    test {
        java {
            setSrcDirs(listOf("test"))
        }
    }
}
