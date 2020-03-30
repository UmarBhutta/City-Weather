# City Weather


City Weather is a simple forecast app, which uses some APIs to fetch 5 day / 3 hour forecast data from the [OpenWeatherMap](https://openweathermap.org/forecast5) using user current location and by city name search. The main goal of this app is to be a sample of how to build an high quality Android application that uses the Architecture components,MVVM, Dagger etc. in Kotlin.

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:UmarBhutta/City-Weather.git
```

## Libraries and tools 🛠

<li><a href="https://developer.android.com/topic/libraries/architecture/navigation/">Navigation</a></li>
<li><a href="https://developer.android.com/training/data-storage/shared-preferences">Shared Preferences</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/livedata">LiveData</a></li>
<li><a href="https://developer.android.com/reference/androidx/lifecycle/Transformations">Transformations</a></li>
<li><a href="https://github.com/ReactiveX/RxJava">RxJava</a></li>
<li><a href="https://github.com/ReactiveX/RxAndroid">RxAndroid</a></li>
<li><a href="https://github.com/ReactiveX/RxKotlin">RxKotlin</a></li>
<li><a href="https://github.com/google/dagger">Dagger 2</a></li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a></li>
<li><a href="https://github.com/square/okhttp">OkHttp</a></li>
<li><a href="https://github.com/square/moshi">Moshi</a></li>
<li><a href="https://material.io/develop/android/docs/getting-started/">Material Design</a></li>
<li><a href="https://github.com/JakeWharton/ThreeTenABP">ThreeTenABP</a></li>

## Testing 🧪
<li><a href="https://github.com/mockk/mockk">Mockk</a></li>
<li><a href="https://github.com/google/truth">Truth</a></li>

## Architecture
The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

## Build variants
Use the Android Studio *Build Variants* button to choose between **release** and **staging** flavors combined with debug and release build types


## Generating  APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

## Maintainers
This project is mantained by:
* [Muhammad Umar](https://github.com/UmarBhutta)

