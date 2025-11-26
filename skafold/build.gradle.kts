plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.21"
    id("com.google.devtools.ksp") version "2.0.21-1.0.26"
    id("maven-publish")
    id("signing")
}

group = "io.oliverj.skafold"
version = "2.0.0"

android {
    namespace = "io.oliverj.skafold"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":processor"))
    ksp(project(":processor"))
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier = "sources"
    from(android.sourceSets["main"].java.srcDirs)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "skafold"

            artifact("$buildDir/outputs/aar/${artifactId}-release.aar")
            artifact(tasks.findByName("sourcesJar"))

            pom {
                name = "Skafold Framework"
                description = "A android framework for creating multipage scouting apps"
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