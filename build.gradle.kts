plugins {
    id("com.gradle.plugin-publish") version "1.2.1"
    `maven-publish`
}

group = "io.github.apdevteam"
version = "1.2.2"

repositories {
    mavenCentral()
}

gradlePlugin {
    website = "https://github.com/apdevteam/gpr-for-gradle"
    vcsUrl = "https://github.com/apdevteam/gpr-for-gradle.git"
    plugins {
        register("github-packages") {
            id = "io.github.apdevteam.github-packages"
            displayName = "GitHub Packages for gradle"
            description = "Cleanly add Github Packages maven repos with credentials in global gradle.properties or env variable (for Github Actions)"
            tags = listOf("github", "github-packages", "dependency", "maven", "repository")
            implementationClass = "io.github.apdevteam.GithubPackagesPlugin"
        }
    }
}
