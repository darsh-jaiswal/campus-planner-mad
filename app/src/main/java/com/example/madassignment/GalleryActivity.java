package com.example.madassignment;
import android.os.Bundle;
import android.widget.LinearLayout;
public class GalleryActivity extends ScreenActivity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout page = createPage("CAMPUS GALLERY", "Local images displayed using ImageView.");
        image(page, R.drawable.campus_art, "Campus building, green trees and the sun");
        label(page, "Campus life", "A place to learn, meet and create.");
        image(page, R.drawable.night_art, "Mountains below a starry night sky");
        label(page, "After hours", "Make space for curiosity and adventure.");
        button(page, "Back to planner", v -> finish());
    }
}
