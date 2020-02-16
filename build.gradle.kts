import java.util.*

plugins {
    kotlin("jvm") version "1.3.61"
    maven
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
}

group = "xyz.sangcomz"
version = "0.0.2"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.github.kittinunf.fuel:fuel:2.2.1")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}


val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
    from("LICENCE.md") {
        into("META-INF")
    }
}


val artifactName = project.name
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()

val pomUrl = "https://github.com/sangcomz/gradle-slack-upload-plugin"
val pomScmUrl = "https://github.com/sangcomz/gradle-slack-upload-plugin"
val pomIssueUrl = "https://github.com/sangcomz/gradle-slack-upload-plugin/issues"
val pomDesc = "https://github.com/sangcomz/gradle-slack-upload-plugin"

val githubRepository = "sangcomz/gradle-slack-upload-plugin"
val githubReadme = "README.md"

val pomLicenseName = "The Apache Software License, Version 2.0"
val pomLicenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
val pomLicenseDist = "repo"

val pomDeveloperId = "sangcomz"
val pomDeveloperName = "Seokwon Jeong"

publishing {
    publications {
        create<MavenPublication>("gradle-slack-upload-plugin") {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion
            artifact(sourcesJar)
            from(components["java"])

            pom.withXml {
                asNode().apply {
                    appendNode("description", pomDesc)
                    appendNode("name", rootProject.name)
                    appendNode("url", pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", pomLicenseName)
                        appendNode("url", pomLicenseUrl)
                        appendNode("distribution", pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", pomDeveloperId)
                        appendNode("name", pomDeveloperName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", pomScmUrl)
                    }
                }
            }
        }
    }
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())
bintray {
    user = localProperties.getProperty("bintrayUser").toString()
    key = localProperties.getProperty("bintrayKey").toString()

    publish = true

    setPublications("gradle-slack-upload-plugin")

    pkg.apply {
        repo = "maven"
        name = artifactName
        userOrg = "sangcomz"
        githubRepo = githubRepository
        vcsUrl = pomScmUrl
        description = "Upload files to Slack using Gradle Plugin"
        setLabels("kotlin", "slack", "uploader", "gradle-plugin")
        setLicenses("Apache-2.0")
        desc = description
        websiteUrl = pomUrl
        issueTrackerUrl = pomIssueUrl
        githubReleaseNotesFile = githubReadme

        version.apply {
            name = artifactVersion
            desc = pomDesc
            released = Date().toString()
            vcsTag = artifactVersion
        }
    }
}
