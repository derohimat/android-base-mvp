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
	        compile 'com.github.derohimat:android-base-mvp:0.1'
	}

### Used Library from :
  - [Retrofit 2](http://square.github.io/retrofit/)
  - [OkHTTP 3](http://square.github.io/okhttp/)
  - [RXJava](https://github.com/ReactiveX/RxJava)
  - [Dagger 2](http://google.github.io/dagger/)
  - [Event Bus](https://nodejs.org/)
  - [XRecyclerView](https://github.com/jianghejie/XRecyclerView)


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
