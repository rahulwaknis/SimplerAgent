package in.getsimpler.simpleragentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //===================================THIS MUST BE A FULL SCREEN ACTIVITY==========================
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_load);

        //=====================================VOLLEY CALL==============================================
        String url = "http://www.shrikanttapkeer.com/simpler_anand/getJobDetails.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("test0",response);
                            Intent in = new Intent(LoadActivity.this,TabbedMainActivity.class);
                            in.putExtra("response",response);
                            startActivity(in);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }

        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("pickupmanid", "32");
                params.put("requestid", "R004291");
                params.put("jobtypeid", "1");
                return params;
            }

            @Override
            protected String getParamsEncoding() {
                return super.getParamsEncoding();
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
        //===========================================================================================

    }
}
