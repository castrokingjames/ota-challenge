plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.kotlin.apt)
}

android {
  namespace = "ph.com.ota.challenge.feature.details"
  compileSdk = 34

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }
}

dependencies {
  implementation(projects.timber)
  implementation(projects.ui.common)
  implementation(projects.ui.compose)
  implementation(projects.ui.viewmodel)
  implementation(projects.ui.glide)
  implementation(projects.di.annotation)

  implementation(projects.model)
  implementation(projects.domain)

  implementation(libs.dagger)
  kapt(libs.dagger.compiler)
}
