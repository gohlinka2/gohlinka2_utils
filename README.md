# gohlinka2_utils
Several utility classes that I like to use in my projects.

## [BaseFragment](gohlinka2-utils2/src/main/java/cz/hlinkapp/gohlinka2_utils2/fragments/BaseFragment.kt)
A base Fragment class containing several utilities, such as methods for handling the included dialogs.
Almost all of the app's fragments should implement this class.

## [Dialogs](gohlinka2-utils2/src/main/java/cz/hlinkapp/gohlinka2_utils2/dialogs)
Several generic dialogs. Use BaseFragment to open them.

## [Utils](gohlinka2-utils2/src/main/java/cz/hlinkapp/gohlinka2_utils2/utils)
Several utility classes. To use [SharedPrefUtil](gohlinka2-utils2/src/main/java/cz/hlinkapp/gohlinka2_utils2/utils/SharedPrefUtil.kt) or [ConnectivityChecker](gohlinka2-utils2/src/main/java/cz/hlinkapp/gohlinka2_utils2/utils/ConnectivityChecker.kt), you need to inject their dependencies using Dagger2.

# Using the library
To use in a project, add Jitpack to your project level gradle file:
```
allprojects {
 repositories {
    jcenter() //add this line
    maven { url "https://jitpack.io" } //add this line
 }
}
```
And in your app's gradle file, add:
```
dependencies {
    implementation 'com.github.gohlinka2:gohlinka2_utils:{current release}' //add this line
}
```

And replace `{current release}` with the [release](https://github.com/gohlinka2/gohlinka2_utils/releases) you want.
That's it!
