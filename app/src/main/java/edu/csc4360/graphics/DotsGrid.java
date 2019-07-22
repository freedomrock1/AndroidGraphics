package edu.csc4360.graphics;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DotsGrid extends View {

    private DotsGame mGame;

    private final int DOT_RADIUS = 40;

    public enum DotSelectionStatus { First, Additional, Last };

   public interface DotsGridListener {
      void onDotSelected(Dot dot, DotSelectionStatus status);
   }

   private DotsGridListener mGridListener;

   private int[] mDotColors;
   private int mCellWidth;
   private int mCellHeight;
   private Paint mDotPaint;
   private Paint mPathPaint;

   // private AnimatorSet mAnimatorSet;

   public DotsGrid(Context context, AttributeSet attrs) {
      super(context, attrs);

      // Used to access the game state
      mGame = DotsGame.getInstance();

      // Get color resources
      mDotColors = getResources().getIntArray(R.array.dotColors);

      // For drawing dots
      mDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

      // For drawing the path between selected dots
      mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      mPathPaint.setStrokeWidth(10);
      mPathPaint.setStyle(Paint.Style.STROKE);
   }

   @Override
   protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
      int boardWidth = (width - getPaddingLeft() - getPaddingRight());
      int boardHeight = (height - getPaddingTop() - getPaddingBottom());
      mCellWidth = boardWidth / DotsGame.GRID_SIZE;
      mCellHeight = boardHeight / DotsGame.GRID_SIZE;
      resetDots();
   }

   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);

      // Draw dots
      for (int row = 0; row < DotsGame.GRID_SIZE; row++) {
         for (int col = 0; col < DotsGame.GRID_SIZE; col++) {
            Dot dot = mGame.getDot(row, col);
            mDotPaint.setColor(mDotColors[dot.color]);
            canvas.drawCircle(dot.centerX, dot.centerY, dot.radius, mDotPaint);
         }
      }

      // Draw connector
      ArrayList<Dot> selectedDots = mGame.getSelectedDots();
      if (!selectedDots.isEmpty()) {
         Path path = new Path();
         Dot dot = selectedDots.get(0);
         path.moveTo(dot.centerX, dot.centerY);

         for (int i = 1; i < selectedDots.size(); i++) {
            dot = selectedDots.get(i);
            path.lineTo(dot.centerX, dot.centerY);
         }

         mPathPaint.setColor(mDotColors[dot.color]);
         canvas.drawPath(path, mPathPaint);
      }
   }

   @Override
   public boolean onTouchEvent(MotionEvent event) {

      // Only execute when a listener exists
      if (mGridListener == null) return true;

      // Determine which dot is pressed
      int x = (int) event.getX();
      int y = (int) event.getY();
      int col = x / mCellWidth;
      int row = y / mCellHeight;
      Dot selectedDot = mGame.getDot(row, col);

      // Return previously selected dot if touch moves outside the grid
      if (selectedDot == null) {
         selectedDot = mGame.getLastSelectedDot();
      }

      // Notify activity that a dot is selected
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
         mGridListener.onDotSelected(selectedDot, DotSelectionStatus.First);
      }
      else if (event.getAction() == MotionEvent.ACTION_MOVE) {
         mGridListener.onDotSelected(selectedDot, DotSelectionStatus.Additional);
      }
      else if (event.getAction() == MotionEvent.ACTION_UP) {
         mGridListener.onDotSelected(selectedDot, DotSelectionStatus.Last);
      }

      return true;
   }

   public void setGridListener(DotsGridListener gridListener) {
      mGridListener = gridListener;
   }

   private void resetDots() {
      for (int row = 0; row < DotsGame.GRID_SIZE; row++) {
         for (int col = 0; col < DotsGame.GRID_SIZE; col++) {
            Dot dot = mGame.getDot(row, col);
            dot.radius = DOT_RADIUS;
            dot.centerX = col * mCellWidth + (mCellWidth / 2);
            dot.centerY = row * mCellHeight + (mCellHeight / 2);
         }
      }
   }
}