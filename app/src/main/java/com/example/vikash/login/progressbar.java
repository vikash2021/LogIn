package com.example.vikash.login;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.vikash.login.R;


public class progressbar extends AppCompatActivity {

    public ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

        init();

        progressBar.setVisibility((View.VISIBLE));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        },3000);

        final Handler handler1=new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                finish();

            }
        },3000);


    }

    private void init() {
        this.progressBar = findViewById(R.id.progressBar);
    }
}

