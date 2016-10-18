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
