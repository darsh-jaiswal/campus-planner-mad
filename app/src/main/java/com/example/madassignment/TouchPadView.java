package com.example.madassignment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import java.util.Locale;
public class TouchPadView extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float x = -1, y = -1;
    public TouchPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
        setOnTouchListener((view, event) -> {
            int action = event.getActionMasked();
            if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE || action == MotionEvent.ACTION_UP) {
                x = event.getX(); y = event.getY(); invalidate();
                if (action == MotionEvent.ACTION_DOWN) getParent().requestDisallowInterceptTouchEvent(true);
                if (action == MotionEvent.ACTION_UP) { getParent().requestDisallowInterceptTouchEvent(false); performClick(); }
                return true;
            }
            if (action == MotionEvent.ACTION_CANCEL) { getParent().requestDisallowInterceptTouchEvent(false); return true; }
            return false;
        });
    }
    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.rgb(225, 231, 251));
        paint.setColor(Color.rgb(63, 81, 181));
        if (x >= 0) canvas.drawCircle(x, y, 22 * getResources().getDisplayMetrics().density, paint);
        paint.setColor(Color.rgb(24, 36, 71));
        paint.setTextSize(16 * getResources().getDisplayMetrics().scaledDensity);
        canvas.drawText(x < 0 ? "Touch here to begin" : String.format(Locale.getDefault(), "X: %.0f   Y: %.0f px", x, y), 16 * getResources().getDisplayMetrics().density, 28 * getResources().getDisplayMetrics().density, paint);
    }
    @Override public boolean performClick() {
        super.performClick();
        Toast.makeText(getContext(), "Touch detected on the pad", Toast.LENGTH_SHORT).show();
        return true;
    }
}
