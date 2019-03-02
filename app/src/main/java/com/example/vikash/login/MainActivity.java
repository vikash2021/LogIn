package com.example.vikash.login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Button btn1;
    EditText login_username, login_password;

    private static final String mainRoot = "http://192.168.43.49/v4/api/tmp/main/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.b2);
        btn1=(Button)findViewById(R.id.b1);
        login_username=(EditText)findViewById(R.id.t1);
        login_password= (EditText)findViewById(R.id.t2);


        String userName = login_username.getText().toString();
        String password = login_password.getText().toString();

         btn1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 startActivity(new Intent(getApplicationContext(),scan.class));
                     VolleyConnect vollyLogin = new VolleyConnect(getBaseContext(),mainRoot + "");
                     vollyLogin.LogIn(login_username.toString(),login_password.toString());
             }
         });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });




    }
}
