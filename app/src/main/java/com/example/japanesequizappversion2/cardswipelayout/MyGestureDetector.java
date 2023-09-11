package com.example.japanesequizappversion2.cardswipelayout;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 200;
    private static final int SWIPE_VELOCITY_THRESHOLD = 200;

    private final ISwipeGesture listener;

    public MyGestureDetector(ISwipeGesture listener) {
        this.listener = listener;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            float diffY = e2.getY() - e1.getY();
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    listener.onSwipeBottom();
                    Log.d("CheckApp", "onSwipeBottom()");
                } else {
                    listener.onSwipeTop();
                    Log.d("CheckApp", "onSwipeTop()");
                }
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public interface ISwipeGesture {
        void onSwipeBottom();
        void onSwipeTop();
    }
}