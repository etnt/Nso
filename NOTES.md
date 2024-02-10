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

