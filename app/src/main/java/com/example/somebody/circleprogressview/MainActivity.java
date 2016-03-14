package com.example.somebody.circleprogressview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private CircleProgressView mCircleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        mCircleBar = (CircleProgressView)findViewById(R.id.circleProgressbar);
//        mCircleBar.setProgress(80);
        mCircleBar.setmTxtHint1("你好");

        new Thread(new Runnable() {
            int i = 50;
            @Override
            public void run() {
                while(true){
                    if(i < 100){
                        mCircleBar.setProgressNotInUiThread(i);
//                        mCircleBar.setProgress(i);
                        i +=5;
                    }else{
//                        mCircleBar.setProgress(100);
                        mCircleBar.setProgressNotInUiThread(100);
                        i = 0;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
