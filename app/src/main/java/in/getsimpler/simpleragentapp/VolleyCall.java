package in.getsimpler.simpleragentapp;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rahul on 10/22/2016.
 */
public class VolleyCall {

    public Context context;
    String urlresponse = "";

    public String GetPOSTResponse(String url, final Map<String, String> paramsmap, Context context1)
    {
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            urlresponse = response;
                            /*JSONObject jsonResponse = new JSONObject(response).getJSONObject("form");
                            String site = jsonResponse.getString("site"),
                                    network = jsonResponse.getString("network");
                            System.out.println("Site: "+site+"\nNetwork: "+network);*/
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
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
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
        Volley.newRequestQueue(context1).add(postRequest);
        return urlresponse;
        //=================================================================================

    }
}
