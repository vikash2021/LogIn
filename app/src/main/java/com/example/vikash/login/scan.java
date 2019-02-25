package com.example.vikash.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class scan extends AppCompatActivity {

    public static TextView resultTextView;
    public static Button scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);


        resultTextView = (TextView)findViewById(R.id.t7);
        scan_btn = (Button)findViewById(R.id.btn3);

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),scanner.class));

            }
        });

    }
}

