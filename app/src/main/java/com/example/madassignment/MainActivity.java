package com.example.madassignment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private final Calendar schedule = Calendar.getInstance();
    private Button date, time;
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            androidx.core.graphics.Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });
        if (state != null) schedule.setTimeInMillis(state.getLong("schedule", schedule.getTimeInMillis()));
        date = findViewById(R.id.dateButton);
        time = findViewById(R.id.timeButton);
        updateDateTime();
        Spinner events = findViewById(R.id.eventSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.events, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        events.setAdapter(adapter);
        date.setOnClickListener(v -> new DatePickerDialog(this, (picker, y, m, d) -> {
            schedule.set(y, m, d); updateDateTime();
        }, schedule.get(Calendar.YEAR), schedule.get(Calendar.MONTH), schedule.get(Calendar.DAY_OF_MONTH)).show());
        time.setOnClickListener(v -> new TimePickerDialog(this, (picker, h, m) -> {
            schedule.set(Calendar.HOUR_OF_DAY, h); schedule.set(Calendar.MINUTE, m); updateDateTime();
        }, schedule.get(Calendar.HOUR_OF_DAY), schedule.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(this)).show());
        findViewById(R.id.galleryButton).setOnClickListener(v -> startActivity(new Intent(this, GalleryActivity.class)));
        findViewById(R.id.previewButton).setOnClickListener(v -> {
            EditText name = findViewById(R.id.nameInput);
            String entered = name.getText().toString().trim();
            if (entered.isEmpty()) { name.setError("Enter your name"); name.requestFocus(); Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show(); return; }
            StringBuilder interests = new StringBuilder();
            for (int id : new int[]{R.id.techCheck, R.id.artCheck, R.id.sportsCheck}) {
                CheckBox box = findViewById(id);
                if (box.isChecked()) { if (interests.length() > 0) interests.append(", "); interests.append(box.getText()); }
            }
            RadioGroup mode = findViewById(R.id.modeGroup);
            RadioButton selected = findViewById(mode.getCheckedRadioButtonId());
            Intent intent = new Intent(this, SummaryActivity.class);
            intent.putExtra("name", entered);
            intent.putExtra("event", events.getSelectedItem().toString());
            intent.putExtra("interests", interests.length() == 0 ? "No interests selected" : interests.toString());
            intent.putExtra("mode", selected.getText().toString());
            intent.putExtra("schedule", schedule.getTimeInMillis());
            startActivity(intent);
            Toast.makeText(this, "Your plan is ready to preview", Toast.LENGTH_SHORT).show();
        });
    }
    private void updateDateTime() {
        date.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(schedule.getTime()));
        time.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(schedule.getTime()));
    }
    @Override protected void onSaveInstanceState(Bundle out) {
        out.putLong("schedule", schedule.getTimeInMillis()); super.onSaveInstanceState(out);
    }
}
