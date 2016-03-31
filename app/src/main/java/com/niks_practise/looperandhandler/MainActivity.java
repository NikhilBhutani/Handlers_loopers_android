package com.niks_practise.looperandhandler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Thread thread;
    Handler handler;
    ProgressBar progressBar;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        button = (Button)findViewById(R.id.button);
        thread = new Thread(new Niksthread());
        thread.start();
        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {

                progressBar.setProgress(msg.arg1);
            }
        };

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this,NextMainActivity.class);
        startActivity(intent);

    }

    class  Niksthread implements Runnable
    {
        @Override
        public void run()
        {


             //when thread.start() is invoked, this method runs to perform background tasks
           for(int i=0;i<100;i++)
           {
               Message message = Message.obtain();  // you can set and send any data or extra argument here.
               message.arg1=i;
               handler.sendMessage(message);
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

        }
    }

}
