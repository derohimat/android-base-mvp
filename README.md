[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android%20Base%20MVP%20Concept-blue.svg?style=flat)](https://android-arsenal.com/details/1/4213)

# Android Base MVP Concept

Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

Step 2. Add the dependency

	dependencies {
	        compile 'com.github.derohimat:android-base-mvp:0.4'
	}

Step 3. Add this library for version 0.4

	//----- The core of Reactive Programming
    compile 'io.reactivex:rxjava:1.1.9'
    compile 'io.reactivex:rxandroid:1.2.1'

Step 4. Modify applicationId "net.derohimat.samplebasemvp" to your package name

Step 5. Profit

Step 6. Star this repository :)

### Requirements :
	Min SDK Version 15
	Target SDK Version 24
	Check the compileSdkVersion, and buildToolsVersion to be the latest
	Build Tools Version 24.0.3
	Java 1.8
	
## How to implement a new screen following MVP

Imagine you have to implement a sign in screen.

1. Create a new package under ui called signin
2. Create an new Activity called ```**ActivitySignIn**```. You could also use a Fragment.
3. Define the view interface that your Activity is going to implement. Create a new interface called ```**SignInView**``` that extends ```**MvpView**```. Add the methods that you think will be necessary, e.g. ```**showSignInSuccessful()**```
4. Create a ```**SignInPresenter**``` class that extends ```**BasePresenter<SignInView>**```
5. Implement the methods in ```SignInPresenter``` that your Activity requires to perform the necessary actions, e.g. ```signIn(String email)```. Once the sign in action finishes you should call ```getView().showSignInSuccessful()```.
6. Create a ```**SignInPresenterTest**``` and write unit tests for ```**signIn(email)**```. Remember to mock the ```**SignInView**``` and also the DataManager.
7. Make your ```**ActivitySignIn** implement **SignInView**``` and implement the required methods like ```**showSignInSuccessful()**```
8. In your activity, inject a new instance of ```**SignInPresenter**``` and call ```**presenter.attachView(this)**``` from onCreate and ```**presenter.detachView()**``` from ```**onDestroy()**```. Also, set up a click listener in your button that calls ```**presenter.signIn(email)**```.



### Used Library from :
  - [Retrofit 2](http://square.github.io/retrofit/)
  - [OkHTTP 3](http://square.github.io/okhttp/)
  - [RXJava](https://github.com/ReactiveX/RxJava)
  - [Dagger 2](http://google.github.io/dagger/)
  - [Event Bus](https://github.com/greenrobot/EventBus)
  - [XRecyclerView](https://github.com/jianghejie/XRecyclerView)
  - [Butterknife 7](https://github.com/JakeWharton/butterknife)


### inspired by

https://github.com/AndreiD/UltimateAndroidTemplateRx

https://github.com/zetbaitsu/Benih


### License
    Copyright 2016 Deni Rohimat
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
