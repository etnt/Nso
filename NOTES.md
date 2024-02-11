# Notes

## Internet permissions and using HTTP

Make sure to add the INTERNET permission in the Manifest file
and also add the use of cleartext HTTP.

    <uses-permission android:name="android.permission.INTERNET" />
    
        <application
            android:usesCleartextTraffic="true"
            ...


## Problem connecting to localhost (127.0.0.1)

    $ cd ./Library/Android/sdk/platform-tools/
    $ ./adb reverse tcp:9080 tcp:9080

Then later:

    $ adb reverse --remove-all

See [also](https://handstandsam.com/2016/02/01/network-calls-from-android-device-to-laptop-over-usb-via-adb/)


## Adding a new Icon

Download an Icon for Android from the [Google Fonts](https://fonts.google.com/icons?icon.platform=android) page.

It does not come in SVG format so ask Copilot to: `convert the content to proper svg format`.

Then in Android Studio:

* on the `res` directory, open the Vector Asset tool.
* select the svg file you have created
* chose a name such as: ic_<name>

Then from the code it can be referred to as: `R.drawable.ic_<name>` and be turned into
an ImageVector as: `ImageVector.vectorResource(id = R.drawable.ic_<name>)`

See [also](https://developer.android.com/studio/write/vector-asset-studio#svg)


## Adding new data source

* Create a new package under `data` (e.g alarms)
* In that package, create a new `Kotlin data class from JSON` (select from under the New menu)
* In the `domain` package create a corresponding data UI class (e.g AlarmUi)
* In the new package, create a new file with a mapper function (from data to ui class), see AlarmMapper.kt
* In the MainViewModel add the corresponding mutable/observer pair to hold the data
* Add the corresponding network API function in NsoApi.kt
* Create the other functions needed in the MainViewModel (e.g similar to Alarm, Inet, etc..)
* Add an intent in MainIntent.kt
* Add the handling of that intent in MainViewModel.kt
* Add corresponding TabPage handling in HomeScreen.kt
* Add refresh handling in MainViewModel.kt
* Add a new menu item in HomeScreen.kt
* Add the new screen composable to view the data (e.g see AlarmsScreen.kt)


