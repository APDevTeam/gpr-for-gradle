plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "io.github.apdevteam"
version = System.getenv("RELEASE_VERSION")?.takeIf { it.isNotBlank() }
    ?: runCatching {
        val sha = ProcessBuilder("git", "rev-parse", "--short", "HEAD")
            .start().inputStream.bufferedReader().readLine() ?: "unknown"
        val tag = ProcessBuilder("git", "describe", "--tags", "--abbrev=0")
            .start().inputStream.bufferedReader().readLine() ?: "untagged"
        val dirty = ProcessBuilder("git", "status", "--porcelain")
            .start().inputStream.bufferedReader().readLine() != null
        if (dirty) "$tag+$sha-dirty" else "$tag+$sha"
    }.getOrElse { "unknown" }

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
