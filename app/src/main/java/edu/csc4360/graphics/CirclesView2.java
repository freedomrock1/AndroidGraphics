package edu.csc4360.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class CirclesView2 extends View {

    private Paint mPaint;
    private Random mRandom;

    // Keep track of circles that are drawn
    private ArrayList<Circle> mCircles;

    // Current circle being drawn
    private Circle mCurrentCircle;

    public CirclesView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRandom = new Random();
        mCircles = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw all previous circles
        for (Circle circle : mCircles) {
            mPaint.setColor(circle.color);
            canvas.drawCircle(circle.x, circle.y, circle.radius, mPaint);
        }

        // Draw current circle
        if (mCurrentCircle != null) {
            mPaint.setColor(mCurrentCircle.color);
            canvas.drawCircle(mCurrentCircle.x, mCurrentCircle.y, mCurrentCircle.radius, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Start drawing a new circle
                int color = Color.argb(150, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
                mCurrentCircle = new Circle(x, y, color);
                break;

            case MotionEvent.ACTION_MOVE:
                // Redraw the circle with a new radius
                mCurrentCircle.radius = (float) Math.hypot(x - mCurrentCircle.x, y - mCurrentCircle.y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                // Finished drawing circle, so add it to list of circles
                mCircles.add(mCurrentCircle);
                mCurrentCircle = null;
                invalidate();
                break;

            case MotionEvent.ACTION_CANCEL:
                // Give up drawing this circle
                mCurrentCircle = null;
                break;
        }

        return true;
    }

    private class Circle {
        public float x;
        public float y;
        public float radius;
        public int color;

        public Circle(float x, float y, int color) {
            this.x = x;
            this.y = y;
            radius = 0;
            this.color = color;
        }
    }
}