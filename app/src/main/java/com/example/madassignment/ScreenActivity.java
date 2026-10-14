package com.example.madassignment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public abstract class ScreenActivity extends AppCompatActivity {
    protected int dp(int value) { return Math.round(value * getResources().getDisplayMetrics().density); }
    protected LinearLayout createPage(String title, String subtitle) {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true); scroll.setBackgroundColor(Color.rgb(243,245,252));
        LinearLayout page = new LinearLayout(this); page.setOrientation(LinearLayout.VERTICAL); page.setPadding(dp(24),dp(24),dp(24),dp(24));
        scroll.addView(page); setContentView(scroll);
        ViewCompat.setOnApplyWindowInsetsListener(scroll, (v, insets) -> {
            androidx.core.graphics.Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left,bars.top,bars.right,bars.bottom); return insets;
        });
        TextView heading = new TextView(this); heading.setText(title); heading.setTextSize(30); heading.setTextColor(Color.rgb(24,36,71)); page.addView(heading);
        label(page, "", subtitle); return page;
    }
    protected void label(LinearLayout page, String title, String value) {
        TextView text = new TextView(this); text.setText(title.isEmpty() ? value : title + "\n" + value); text.setTextSize(18); text.setTextColor(Color.rgb(24,36,71)); text.setPadding(0,dp(12),0,dp(12)); page.addView(text);
    }
    protected void image(LinearLayout page, int resource, String description) {
        ImageView image = new ImageView(this); image.setImageResource(resource); image.setContentDescription(description); image.setScaleType(ImageView.ScaleType.FIT_CENTER); page.addView(image, new LinearLayout.LayoutParams(-1,dp(200)));
    }
    protected void button(LinearLayout page, String text, View.OnClickListener listener) {
        Button button = new Button(this); button.setText(text); button.setMinHeight(dp(52)); button.setOnClickListener(listener); page.addView(button, new LinearLayout.LayoutParams(-1,-2));
    }
}
