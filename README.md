# A best practice sample app to show list of Albums.

This is a sample app that is use 'https://jsonplaceholder.typicode.com/' api to show list of Albums.

## About Application

I use Kotlin programming language to write classes.
For views I used Jetpack Compose and legacy xml in diffrent activities so when you build the code you can see two diffrent app icons.
It is a modular application so There are 3 main modules to implement a clean architecture. App is a presentation layer and there are Domain and Data layer. Also there are 2 core modules.
Two diffrent ways have been used for transfering data between data layer and UI layer, Flows and Suspend Functions.
I use Hilt for dependency injection and Coroutine for concurrency.
For loading images I use Coil with storage cache.
Parts of code have been tested by Unit Test and UI Test.


## Tech Stack

* Clean Architecture
* MVVM Architecture
* Coroutines
* Jetpack Compose
* Hilt (DI)
* Kotlin Flow
* Retrofit (Network)
* Coil (Image Loader)
* Unit Test
* UI Test
