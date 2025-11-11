plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("maven-publish")
    id("signing")
}

group = "io.oliverj.skafold"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11

    withSourcesJar()
    withJavadocJar()
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:2.0.21-1.0.26")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "processor"

            from(components["java"])

            pom {
                name = "Skafold Annotation Processor"
                description = "Annotation processor for the skafold framework"
                url = "https://github.com/olliejohnson/skafold.git"

                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/license/mit"
                    }
                }

                developers {
                    developer {
                        id = "olliejohnson"
                        name = "Oliver Johnson"
                        email = "oliver@oliverj.io"
                    }
                }

                scm {
                    connection = "scm:git:git://github.com/olliejohnson/skafold.git"
                    developerConnection = "scm:git:ssh://github.com:olliejohnson/skafold.git"
                    url = "https://github.com/olliejohnson/skafold"
                }
            }
        }
    }

    repositories {
        mavenCentral()
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["maven"])
}
