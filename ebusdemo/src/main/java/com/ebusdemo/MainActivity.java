package com.ebusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReg = (Button)findViewById(R.id.button);
        btnReg.setOnClickListener(this);
        testEventBus();
    }
    @Subscribe(eventId = 1000, sticky = true)
    public void print(String s) {
        System.out.println("str is: " + s);
    }

    @Subscribe
    public void print2(String s) {
        System.out.println("str2 is: " + s);
    }

    public void testEventBus() {

        EventBus.getDefault().post(1000, "id 1000");
        EventBus.getDefault().post(1001, "id 1001");
        EventBus.getDefault().post("id is null");
        EventBus.getDefault().postSticky(1000,"sticky id 1000");
        EventBus.getDefault().postSticky("sticky id null");
        EventBus.getDefault().postSticky(2,"sticky id 2");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                EventBus.getDefault().register(this);
                break;
        }
    }
}
