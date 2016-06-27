package com.ebusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testEventBus();
    }
    @Subscribe(eventId = 1000)
    public void print(String s) {
        System.out.println("str is: " + s);
    }

    @Subscribe
    public void print2(String s) {
        System.out.println("str2 is: " + s);
    }

    public void testEventBus() {
        EventBus.getDefault().register(this);
//        EventBus.getDefault().post(1000, "id 1000");
//        EventBus.getDefault().post(1001, "id 1001");
//        EventBus.getDefault().post("id is null");
        EventBus.getDefault().postSticky(1000,"sticky id 1000");
        EventBus.getDefault().postSticky("sticky id null");
        EventBus.getDefault().postSticky(2,"sticky id 2");
    }
}
