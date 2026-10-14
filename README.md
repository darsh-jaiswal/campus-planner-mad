# Campus Planner - MAD Android Assignment

Java Android app demonstrating EditText, CheckBox, RadioButton, Spinner, date/time pickers, touch listeners, Toasts, screen layouts, images and navigation across three activities.

## Run

Open this project in Android Studio, sync Gradle, select an Android device or emulator and run the app module. Minimum Android version: 7.0 (API 24).

See [APP_DEMO.md](APP_DEMO.md) for the demonstration flow and implementation map.

## Web deployment

`web/` contains the presentation website, screenshots and the verified debug APK. Vercel serves this directory using the root `vercel.json`; the Android app runs on Android rather than within Vercel.

## Verification

- Debug APK successfully assembled.
- Installed and launched on Pixel emulator.
- Summary and gallery navigation checked with displayed data.
- Date and time dialogs checked.
- Touch coordinates and Toast visually verified.

Confirmation is a local UI demonstration and does not persist an event booking.
