package com.example.malli.myapplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by malli on 3/10/2016.
 */

public class JSONParser {
    HttpURLConnection conn;
    URL url = null;
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() throws IOException, JSONException {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    public JSONObject makeHttpRequest(String url2 , String method,
                                      String name, String email, String password) {

        // Making HTTP request
        try {

            // check for request method
            if (method == "POST") {
                // request method is POST
                url = new URL(url2);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); //After or before of setDoOutput(true) for organization :)
                conn.setChunkedStreamingMode(0);
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setUseCaches(false);
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                //conn.setRequestProperty("Content-Type", "application/json");
                //conn.setRequestProperty("Host", "android.schoolportal.gr");
                conn.connect();


                JSONObject jsonParam = new JSONObject();
                jsonParam.put("name", name);
                jsonParam.put("email", email);
                jsonParam.put("password", password);
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(jsonParam.toString());
                out.close();

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        /*try {
            int HttpResult =conn.getResponseCode();
            if(HttpResult ==HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
               // is.close();

                json = sb.toString();
                Log.d("json",String.valueOf(json));
            }
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
*/
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        Log.d("json3",String.valueOf(json));
        // return JSON String
        return jObj;

    }

    public JSONObject makeHttpRequest2(String url2 , String method,
                                      String name, String password) throws IOException {

        // Making HTTP request
        try {

            // check for request method
            if (method == "POST") {
                // request method is POST

                url = new URL(url2);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); //After or before of setDoOutput(true) for organization :)
                conn.setChunkedStreamingMode(0);
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setUseCaches(false);
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                //conn.setRequestProperty("Content-Type", "application/json");
                //conn.setRequestProperty("Host", "android.schoolportal.gr");
                conn.connect();


                JSONObject jsonParam = new JSONObject();
                jsonParam.put("name", name);
                //jsonParam.put("email", email);
                jsonParam.put("password", password);
                Log.d("json",String.valueOf(jsonParam));
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(jsonParam.toString());
                out.close();


            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object

        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        Log.d("json3",String.valueOf(json));
        // return JSON String
        return jObj;

    }
}