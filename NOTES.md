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
