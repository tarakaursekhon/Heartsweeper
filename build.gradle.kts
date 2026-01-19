plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("C:/Program Files (x86)/Rocket Software/extend 11.0.0/AcuGT/tools/CVM.jar"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("org.example.Main")
}

tasks.withType<JavaExec> {
    systemProperty("java.library.path", "C:/Program Files (x86)/Rocket Software/extend 11.0.0/AcuGT/bin")
}

// Task to copy COBOL files to the build output directory
val copyCobolFiles = tasks.register<Copy>("copyCobolFiles") {
    from("src/main/java") {
        include("**/*.acu")
    }
    into("temp")
}

// Make the compileJava task depend on copying COBOL files
tasks.named("compileJava") {
    dependsOn(copyCobolFiles)
}
