package com.example.vikash.login;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Map;

public class scan extends AppCompatActivity {

   // public Array imgGroup ={{}}

    private static final String TAG = "scan";

    public  TextView resultTextView;
    private Context mContext;
    private Button scan_btn;

    private static final String mainRoot = "http://192.168.43.49/v4/api/tmp/main/";

    final String url = "http://192.168.137.119/v4/api/tmp/hacktemp_insert_new_data.php";

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

                                      ////<<<<<<<--------------------------------------------------------<<<<<<<<<<<<<<<<<


    }

    private void Init() {
        mContext = scan.this;
        resultTextView = (TextView) findViewById(R.id.t7);
        scan_btn = (Button) findViewById(R.id.btn3);
       // login_btn = (Button) findViewById(R.id.logOut);
    }

    void scanQr(){
        IntentIntegrator integrator = new IntentIntegrator(scan.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan QRCode");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.setOrientationLocked(true);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (intentResult.getContents() == null){
            Toast.makeText(mContext, "You cancelled the scan",Toast.LENGTH_SHORT).show();
        } else {
            resultTextView.setText(intentResult.getContents());
            resultTextView.setTextSize(16.0f);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(getApplicationContext(), confirmationPage.class));
                }
            }, 2000);


            String qr_code = intentResult.getContents().toString();
            String ip = "9999999";
            String otp = "0000";
            String pk = "0";
            String ok = "1";
            String dates = "01/02/2019";

            vollyConnect(url, qr_code, ip, otp, pk,ok,dates);


        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void vollyConnect(final String urls, String qr, String ip, String otp, String pk,String ok,String dates)
    {

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();

            // init this json object
            jsonBody.put("qr_number", qr);
            jsonBody.put("ip_address", ip);
            jsonBody.put("otp", otp);
            jsonBody.put("pk", pk);
            jsonBody.put("ok", ok);
            jsonBody.put("date", dates);


            final String mRequestBody = jsonBody.toString();
            Log.d(TAG, "vollyConnect: "+mRequestBody);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, urls, new
                    Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("LOG_VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                    }
                    return Response.success(responseString,
                            HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}






































































//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    RequestQueue queue = Volley.newRequestQueue(this);
//
//        Log.d(TAG, "connect: param: " + "pr: " + qr + " mobile_id: " + mobile_id + " otp : " + otp + " pk : " + pk);
//
//final String url = "http://192.168.43.49/v4/api/tmp/hacktemp_insert_new_qr.php";
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//        new Response.Listener<String>()
//        {
//@Override
//public void onResponse(String response) {
//        // response
//        Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "onResponse: Response: " + response);
//        }
//        },
//        new Response.ErrorListener()
//        {
//@Override
//public void onErrorResponse(VolleyError error) {                     //------------------------------/
//        // error
//        Toast.makeText(mContext, "Error: " + error, Toast.LENGTH_SHORT).show();
//        Log.d("Error.Response", error.toString());
//        }
//        }
//        ) {
//@Override
//protected Map<String, String> getParams()
//        {
//        // creating json object
//        JSONObject jsonObject = new JSONObject();
//
//        // init json object
//        try {
//        jsonObject.put("qr" , qr);
//        jsonObject.put("mobile_id" , mobile_id);
//        jsonObject.put("otp" , otp);
//        jsonObject.put("pk" , pk);
//        } catch (JSONException e) {
//        e.printStackTrace();
//        }
//
//        return jsonObject;
//        }
//        };
//
//        queue.add(postRequest);
//
//
//
//
////        try{
////
////            RequestQueue referenceQueue = Volley.newRequestQueue(this);
////
//////
//////
//////            // creating json object
//////            JSONObject jsonObject = new JSONObject();
//////
//////            // init json object
//////            jsonObject.put("qr" , qr);
//////            jsonObject.put("mobile_id" , mobile_id);
//////            jsonObject.put("otp" , otp);
//////            jsonObject.put("pk" , pk);
//////
//////            final String requestBody = jsonObject.toString();
//////
////////            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
////////                @Override
////////                public void onResponse(String response) {
////////
////////                }
////////            }), new Response.ErrorListener(){
////////
////////            }
//////
//////            final String url = "http://localhost/v4/api/tmp/hacktemp_insert_new_qr.php";
//////
//////            StringRequest postRequest = new StringRequest(Request.Method.POST, url, getPo
//////
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//        }
//
//
//
//    /*public static synchronized List generateRandomPin(){
//
//        int start  = 1000000000;
//        long end = 9999999999L;
//
//        Random random = new Random();
//        for (int i=1;i<=3000;++i)
//        {
//            createRandomPin(start,end,random);
//        }
//        return null;
//    }
//
//
//    private static void createRandomPin(int aStart, long aEnd, Random aRandom)
//    {
//        if(aStart>aEnd)
//        {
//            throw new IllegalArgumentException("Start cannot exceed end");
//
//        }
//        long range = (long)aEnd-(long)aStart+1;
//        logger.info("range>>>>>>>>>>>>>>>>"+range);
//        long fraction =(long)(range*aRandom.nextDouble());
//        logger.info("fraction>>>>>>>>>>>>"+fraction);
//        int randomNumber = (int)(fraction+aStart);
//        logger.info("Generated"+randomNumber);
//
//    }
//    */
