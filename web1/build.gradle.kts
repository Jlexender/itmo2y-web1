plugins {
    id("java")
}

group = "ru.lexender.ifmo.web1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("libs/fastcgi-lib.jar"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "ru.lexender.ifmo.web1.FcgiServer"
        )
    }
}

tasks.register<Jar>("deploy") {
    archiveClassifier.set("all")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes(
            "Main-Class" to "ru.lexender.ifmo.web1.FcgiServer"
        )
    }
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    doLast {
        val targetDir = file("httpd-root/fcgi-bin")
        if (!targetDir.exists()) {
            targetDir.mkdirs()
        }
        val targetFile = targetDir.resolve("server.jar")
        archiveFile.get().asFile.copyTo(targetFile, overwrite = true)
    }
}
