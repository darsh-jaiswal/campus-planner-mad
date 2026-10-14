package com.example.madassignment;
import android.os.Bundle;
import android.widget.*;
import java.text.DateFormat;
import java.util.Date;
public class SummaryActivity extends ScreenActivity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout page = createPage("YOUR PLAN", "Ready for your next experience?");
        image(page, R.drawable.campus_art, "Campus illustration");
        label(page, "Name", getIntent().getStringExtra("name"));
        label(page, "Event", getIntent().getStringExtra("event"));
        label(page, "Interests", getIntent().getStringExtra("interests"));
        label(page, "Attendance", getIntent().getStringExtra("mode"));
        Date date = new Date(getIntent().getLongExtra("schedule", System.currentTimeMillis()));
        label(page, "When", DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date));
        button(page, "Confirm plan", v -> Toast.makeText(this, "Plan confirmed! This is a local demonstration.", Toast.LENGTH_LONG).show());
        button(page, "Edit my plan", v -> finish());
    }
}
