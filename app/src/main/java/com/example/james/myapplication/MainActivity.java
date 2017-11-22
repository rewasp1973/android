package com.example.james.myapplication;

import android.graphics.Color;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.GestureDetector;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Added 22-11-2017
    GestureDetectorCompat gestureObj;
    MediaPlayer mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Added 22-11-2017
        gestureObj = new GestureDetectorCompat(this, new LearnGesture());   // LearnGesture is a class file
        mySong = MediaPlayer.create(MainActivity.this, R.raw.burn);

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
    // on even the OS puts the caught event. I get the action of the event as a parameter of the switch case
    //              if the returned value is 0 means screen touched and kept on touching
    //              if the returned value is 1 means screen not touched
    // public method by which I drive the touch event
    public boolean onTouchEvent(MotionEvent event) {
        // Added 22-11-2017
        this.gestureObj.onTouchEvent(event);

        TextView txtChanger = (TextView) findViewById(R.id.txt);
        ConstraintLayout backgroundColour = (ConstraintLayout) findViewById(R.id.frameLayout);

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                ((TextView)txtChanger).setTextColor(0xFFFF0000);                     // red
                ((TextView)txtChanger).setBackgroundColor(0xFFFFFF00);               // yellow fluo
                ((ConstraintLayout)backgroundColour).setBackgroundColor(0xFFFF0000); // red
                Random rnd = new Random();
                txtChanger.setText("TOUCHED");
                break;

            case MotionEvent.ACTION_UP:
                ((TextView)txtChanger).setTextColor(0xFF0000FF);                     // blue
                ((TextView)txtChanger).setBackgroundColor(0xFF00FF00);               // green fluo
                ((ConstraintLayout)backgroundColour).setBackgroundColor(0xFF0000FF); // blue
                txtChanger.setText("NOT TOUCHED");
                break;

            case MotionEvent.ACTION_MOVE:
                ((TextView)txtChanger).setTextColor(0xFF007500);                     // green
                ((TextView)txtChanger).setBackgroundColor(0xFF00FFFF);               // cyan
                ((ConstraintLayout)backgroundColour).setBackgroundColor(0xFF007500); // green
                txtChanger.setText("SWIPED");
                break;

            default:
                break;

        }
        /* txtChanger.setText(String.valueOf(event.getAction()));*/
        /* return true; */
        // Added 22-11-2017
        return super.onTouchEvent(event);
    }

    // Added 22-11-2017
    // gestureObj creation
    class LearnGesture extends GestureDetector.SimpleOnGestureListener {

        // SimpleOnGestureListener is the listener for what we want to do and how we what do to it
        // Setting the required code for gesture side by side
        @Override
        public boolean onFling(MotionEvent eventOne, MotionEvent eventTwo, float xSpeed, float ySpeed) {
            if (eventTwo.getX() > eventOne.getX()) {
                
                // opening new activity by touching
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                finish(); // finish() is used to stop history for MainActivity class
                startActivity(intent);
                if (mySong.isPlaying()) {
                    mySong.pause();
                }

            } else {
                if (eventTwo.getX() < eventOne.getX()) {
                    // do something else opening by touch or anything
                    mySong.start();
                }
            }
            return true;
        }
    }
}
