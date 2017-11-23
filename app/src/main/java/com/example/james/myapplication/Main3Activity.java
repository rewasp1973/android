package com.example.james.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.GestureDetector;

public class Main3Activity extends AppCompatActivity {

    GestureDetectorCompat gestureObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        gestureObj = new GestureDetectorCompat(this, new LearnGesture());
    }

    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObj.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    // page management
    class LearnGesture extends GestureDetector.SimpleOnGestureListener {
        // Page management
        public boolean onFling(MotionEvent eventOne, MotionEvent eventTwo, float xSpeed, float ySpeed) {
            // page backward
            if (eventTwo.getX() > eventOne.getX()) {
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                finish();
                startActivity(intent);

            } else {
                if (eventTwo.getX() < eventOne.getX()) {

                }
            }
            return true;
        }
    }
}
