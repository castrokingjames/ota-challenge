[![CI](https://github.com/castrokingjames/ota-challenge/actions/workflows/build.yaml/badge.svg)](https://github.com/castrokingjames/ota-challenge/actions/workflows/build.yaml)

Libraries Used
--------------
* [Compose][1] - Android modern toolkit for building native UI
* [Foundation][2] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [Android KTX][3] - Write more concise, idiomatic Kotlin code.
  * [Material3][4] - Material Design 3 library for android
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* Third party and miscellaneous libraries
  * [Mavericks][99] - Android MVI framework 
  * [Dagger][92] - for [dependency injection][93]
  * [Retrofit][55] - A type-safe HTTP client for Android and Java
  * [SQLDelight][43] - Generates typesafe Kotlin APIs from SQL
  * [Glide][65] - An image loading and caching library for Android focused on smooth scrolling
  * [Kotlin Coroutines][91] - for managing background threads with simplified code and reducing needs for callbacks

[1]: https://developer.android.com/develop/ui/compose
[2]: https://developer.android.com/jetpack/components
[3]: https://developer.android.com/kotlin/ktx
[4]: https://m3.material.io/develop/android/jetpack-compose
[10]: https://developer.android.com/jetpack/arch/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[30]: https://developer.android.com/guide/topics/ui
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[92]: https://developer.android.com/training/dependency-injection/dagger-android
[93]: https://developer.android.com/training/dependency-injection
[99]: https://github.com/airbnb/mavericks
[44]: https://firebase.google.com/docs/ml-kit/recognize-text
[43]: https://github.com/cashapp/sqldelight
[55]: https://square.github.io/retrofit/
[65]: https://square.github.io/retrofit/