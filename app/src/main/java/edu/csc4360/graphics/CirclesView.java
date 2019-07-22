package edu.csc4360.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;


public class CirclesView extends View {

    private int mNumCircles = 5;
    private Paint mPaint;
    private Random mRandom;

    public CirclesView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRandom = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int c = 0; c < mNumCircles; c++) {
            int randomColor = Color.argb(150, mRandom.nextInt(256),
                    mRandom.nextInt(256), mRandom.nextInt(256));
            mPaint.setColor(randomColor);
            float centerX = mRandom.nextInt(getWidth());
            float centerY = mRandom.nextInt(getHeight());
            float radius = mRandom.nextInt(200) + 100;
            canvas.drawCircle(centerX, centerY, radius, mPaint);
        }
    }

    public void setNumCircles(int numCircles) {
        mNumCircles = numCircles;

        // Redraw the view
        invalidate();
    }
}
