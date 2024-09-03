plugins {
    id("java")
    id("checkstyle")
    id("war")
}

group = "ru.lexender"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.check {
    dependsOn("checkstyleMain", "checkstyleTest")
}
