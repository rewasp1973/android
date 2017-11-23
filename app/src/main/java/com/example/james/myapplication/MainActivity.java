package com.example.james.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.GestureDetector;

public class MainActivity extends AppCompatActivity {
    GestureDetectorCompat gestureObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gestureObj = new GestureDetectorCompat(this, new LearnGesture());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObj.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    // gestureObj creation
    class LearnGesture extends GestureDetector.SimpleOnGestureListener {

        // SimpleOnGestureListener is the listener for what we want to do and how we what do to it
        // Setting the required code for gesture side by side
        @Override
        public boolean onFling(MotionEvent eventOne, MotionEvent eventTwo, float xSpeed, float ySpeed) {
            // page backward
            if (eventTwo.getX() > eventOne.getX()) {

            } else {
                // page forward
                if (eventTwo.getX() < eventOne.getX()) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    finish();
                    startActivity(intent);
                }
            }
            return true;
        }
    }
}
