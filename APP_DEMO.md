# Campus Planner - MAD assignment

Java Android application built in this project. No internet permission or external service is needed by the app.

## Required components

| Requirement | Implementation |
| --- | --- |
| Check boxes | Choose Technology, Art and music, and Sports independently. |
| Radio buttons | Choose In person or Online; only one can be selected. |
| Screen layout | ScrollView with a vertical LinearLayout and a horizontal RadioGroup. |
| Spinner | Select one of four campus events. |
| Date picker | DatePickerDialog opened by the date button. |
| Time picker | TimePickerDialog opened by the time button. |
| Touch listener | Tap or drag the touch pad; a circle and X/Y coordinates follow the gesture. |
| Toasts | Empty-name feedback, preview feedback, touch feedback and confirmation. |
| Multiscreen application | MainActivity, SummaryActivity and GalleryActivity; explicit intents pass the plan. |
| Displaying images | ImageView displays two bundled vector illustrations. |
| EditText | Name input with required-field validation. |

## Run and demonstrate

1. Open this project in Android Studio and allow Gradle sync to finish.
2. Select the Pixel emulator or a connected Android device and click the green Run button.
3. Enter a name, select interests, attendance mode and an event.
4. Open both pickers and choose a date and time.
5. Tap and drag the touch pad to show the listener and Toast.
6. Tap Preview my plan to open the summary with the selected values.
7. Tap Confirm plan to demonstrate the confirmation Toast; Edit my plan returns to the form.
8. Open Explore image gallery to show the second image screen; Back to planner returns.

Confirmation is a local UI demonstration. It does not book an event or persist a booking. Form controls and schedule survive activity recreation using Android view state and a saved Bundle.

## Main files

- `app/src/main/res/layout/activity_main.xml`: planner screen layout.
- `app/src/main/java/com/example/madassignment/MainActivity.java`: form, pickers and intents.
- `TouchPadView.java`: touch listener and drawing.
- `SummaryActivity.java`: transferred plan and Toast.
- `GalleryActivity.java`: bundled images.
- `ScreenActivity.java`: shared secondary-screen layout helpers.
- `app/src/main/res/drawable/campus_art.xml` and `night_art.xml`: images.
