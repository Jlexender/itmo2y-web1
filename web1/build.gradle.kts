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
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.check {
    dependsOn("checkstyleMain", "checkstyleTest")
}
