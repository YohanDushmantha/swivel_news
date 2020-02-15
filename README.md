# Swivel News Feed Demo App

<a href="http://www.youtube.com/watch?feature=player_embedded&v=VpFb7lyObno
" target="_blank"><img src="http://img.youtube.com/vi/VpFb7lyObno/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>

## Getting Started
Download [apk](https://drive.google.com/file/d/1SMj-lZ6k9w5lg8GFhkSJBNFH3GNXyf1f/view?usp=sharing)

create user account using user registration and login into account. 

limitations - You can create multiple user accounts, but only last one will be accessible

# Architecture of the application

## Used Languages, Technologies and Libraries

* Kotlin
* Multi Module Architecture
* Android Architecture Components
* Dagger 2
* Navigation UI
* Data Binding
* Coroutines
* Shared Preferences
* Retrofit
* Glide

## Application Architecture Module Tree

application module structure has been organized in following manner

* app module (main module) - initialize application and injecting dependencies
* core modules - core modules of the application
  * config module - contains of common configurations related to application
  * crypto module - handle encryption and decryption
  * navigation module - handle navigation of the application
  * security module - handle session and authentication data
  * ui module - ui related utilities
  * utility module - other utilities
  * validator module - handle form validation
* data modules - data handling sector of the application
  * data sources - data sources which is provided data to application
    * local module - provide capability for handling local db using Room library
    * remote module - provide capability for handling remote data source using retrofit
    * shared preferences module - provide capability to cache data inside shared preferences (secured with crypto)
    * amazon module - provision for adding amazon api like s3 bucket
    * firebase module - provision for adding firebase module
  * models module - model classes which is commonly used
  * repository modules - repository module for handling data layer
* feature modules - feature modules
  * onboarding module - onboard user until walkthrough
  * registration module - user registration module
  * login module - login module
  * home module - home module
  * shared module - contains of shared features. 





