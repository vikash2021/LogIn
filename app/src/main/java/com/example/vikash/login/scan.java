package com.example.vikash.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Result;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scan extends AppCompatActivity {

    public  TextView resultTextView;
    private Context mContext;
    private Button scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        Init();

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQr();
            }
        });

    }

    private void Init() {
        mContext = scan.this;
        resultTextView = (TextView) findViewById(R.id.t7);
        scan_btn = (Button) findViewById(R.id.btn3);
    }

    void scanQr(){
        IntentIntegrator integrator = new IntentIntegrator(scan.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (intentResult.getContents() == null){
            Toast.makeText(mContext, "u cancelled the scan.",Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(mContext, intentResult.getContents() ,Toast.LENGTH_SHORT).show();
            resultTextView.setText(intentResult.getContents().toString());
            resultTextView.setTextSize(16.0f);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

}

