package com.niks_practise.looperandhandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;



/**
 * Created by Nikhil Bhutani on 3/30/2016.
 *
 *
 * Looper Example, Main Thread to Background Thread Communication
 *
 */
public class NextMainActivity extends Activity implements View.OnClickListener {

    NiksThread niksThread;
    Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity_main);
        sendMessageButton = (Button)findViewById(R.id.sendMessage);
        niksThread = new NiksThread();
        niksThread.start();
        sendMessageButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        niksThread.handler.post(new Runnable() {
            @Override
            public void run() {
                L.m(Thread.currentThread().getName());
            }
        });

    }
}
class NiksThread extends Thread {
//this class will be capable of having its own message queue and proccess its own messages

    Handler handler; // handle incoming messages to NiksThread

    //constructor
    public NiksThread(){

    }

    //code for background thread is written inside the run method.
    @Override
    public void run() {

        Looper.prepare(); // makes this thread, which can have a message queue and capable of handling its own messages

        handler = new Handler();  //wherever the handler is initialized with which you can send messages to.
        Looper.loop(); //this is going to pick up one message at a time from our message queue and process it with help of handler
    }
}