// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("io.realm.kotlin") version "1.16.0" apply false
}

configurations.all {
    resolutionStrategy {
        force("com.google.protobuf:protobuf-java:3.19.6")
        force("org.bouncycastle:bcprov-jdk18on:1.78")
        force("com.github.stephenc.jcip:jcip-annotations:1.0-1")
    }
    exclude(group = "com.google.protobuf", module = "protobuf-javalite")
    exclude(group = "org.bouncycastle", module = "bcprov-jdk15to18")
    exclude(group = "net.jcip", module = "jcip-annotations")
    exclude(group = "com.google.crypto.tink", module="tink")
}