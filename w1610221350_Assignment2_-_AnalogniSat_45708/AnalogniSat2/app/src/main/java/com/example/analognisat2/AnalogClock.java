package com.example.analognisat2;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class AnalogClock extends View {
    private Paint paint;
    private int height = 30;
    private int width = 30;
    private int padding = 20;
    private int handTruncation, hourHandTruncation = 5;
    private int radius = 20;
    Bitmap backgroundBitmap;

    public AnalogClock(Context context) {
        super(context);
    }

    public AnalogClock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init (Context context, AttributeSet attrs) {
        paint = new Paint();
        backgroundBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_background);
        height = getHeight();
        width = getWidth();
        int min = Math.min(height, width);
        radius = min / 2;
        handTruncation = min / 10;
        hourHandTruncation = min / 10;
    }


    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(backgroundBitmap, null, null);
        drawCircle(canvas);
        Hands(canvas);
        postInvalidateDelayed(1000);
        invalidate();

    }

    private void drawCircle (Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        paint.setTextSize(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.isAntiAlias();
        canvas.drawCircle(width, height, radius, paint);

    }
    private void Hands (Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Calendar c = Calendar.getInstance();
        float hour = c.get(Calendar.HOUR);
        hour = hour > 12 ? hour - 12 : hour;
        Hand (canvas, (hour + c.get(Calendar.MINUTE)/60.0)*10f, true);
        Hand (canvas, c.get(Calendar.MINUTE), true);
        Hand (canvas, c.get(Calendar.SECOND), true);
    }

    private void Hand (Canvas canvas, double d, boolean b) {
        double loc = -1;
        double angle = Math.PI * loc / 30 - Math.PI/2;
        boolean isHour = false;
        int handRadius = isHour ? radius - handTruncation - hourHandTruncation : radius - handTruncation;
        canvas.drawLine(width / 2, height / 2, (float) (width / 2 + Math.cos(angle) * handRadius),
                (float) (height / 2 + Math.sin(angle) * handRadius),paint);
    }

}