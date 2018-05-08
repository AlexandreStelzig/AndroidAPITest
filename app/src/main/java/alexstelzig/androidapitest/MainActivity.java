package alexstelzig.androidapitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    // use 10.0.2.2 for emulator (localhost)
    // if you want to use your device, makes sure that it is connected to the same network
    // also change the address to your computer ip (not public ip - do ipconfig - under inet)
    final String API_IP = "http://192.168.0.10:3000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // BOOLEAN REQUEST EXAMPLE
        try {
            String url = API_IP + "validatecredentials";
            JSONObject jsonBody;
            jsonBody = new JSONObject();
            jsonBody.put("userName", "admin");
            jsonBody.put("password", "admin");
            String requestBody = jsonBody.toString();
            BooleanRequest booleanRequest = new BooleanRequest(Request.Method.POST, url, requestBody,
                    new Response.Listener<Boolean>() {
                        @Override
                        public void onResponse(Boolean response) {
                            Log.d("TEST", "Response: " + response.toString());
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TEST", "ERROR: " + error.toString());
                }
            });
            // Add the request to the RequestQueue.
            MySingleton.getInstance(this).addToRequestQueue(booleanRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // STRING REQUEST EXAMPLE
        String url = API_IP;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TEST", "Response: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("TEST", "Error: " + error.toString());
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
