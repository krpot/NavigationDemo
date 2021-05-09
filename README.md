**Issue to solve**
This repository demonstrates how to handle conditional navigation between onboarding and login flow using ViewModel(LiveData) + Jetpack Navigation Component + Bottom NavigationView + Splashscreen.

In your navigation graph, you cannot set `app:startingDestion="splashFragment"` simply. If you do that but don't add SplashFragment to the backstack again, your navigation will work like web browser. Fragments linked bottom navigation menu will be put in the backstack whenever you click menu from there instead of using the existing fragment in the backstack. 
If you remove startingDestination from backstack but don't add it again to backstack will have the same issue. So, you should be careful with it when you remove startingDestination.

**Navigation logic with LiveData**
One thing you shoud keep in mind that is you need to use `SingleLiveEvent` or `liveData` extension to avoid ping-pong issue when you use Navigation Component with `LiveData` class. Becuase if you have navigation logic in LiveData's observe code in Fragmen a, and then navigate to Fragment b and LiveData will be automatically emitted when you go back to Fragment a.

**Test Users**
Thery demo user list in com.mpark.navigationdemo.data.UserDatabase

