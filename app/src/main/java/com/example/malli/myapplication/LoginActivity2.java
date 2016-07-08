package com.example.malli.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.malli.myapplication.app.AppConfig;
import com.example.malli.myapplication.helper.SQLiteHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by malli on 3/11/2016.
 */
public class LoginActivity2 extends Activity {

    private static final String TAG = LoginActivity2.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;

    private Button btnRegister;
   // private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    //private SessionManager session;
    private SQLiteHandler db;
    //  private ProgressDialog pDialog;
    private FloatingActionButton fab;
    JSONParser jsonParser;
    // url to create new product
   // String url_create_product = "http://192.168.1.5:3000/login";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetector cd;
    private CoordinatorLayout coordinatorLayout;
    public LoginActivity2() throws IOException, JSONException {
        jsonParser = new JSONParser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        // creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());
        // Edit Text
        inputFullName = (EditText) findViewById(R.id.name);
      //  inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);

        db = new SQLiteHandler(getApplicationContext());
        db.deleteUsers();
        // Create button
        Button btnlogin = (Button) findViewById(R.id.btnLogin);
        //fab = (FloatingActionButton) findViewById(R.id.fab);
        Button btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // button click event
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });



        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                 //view = this.getCurrentFocus();
           /*     if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
*/
                isInternetPresent = cd.isConnectingToInternet();

                // check for Internet status
                if (isInternetPresent) {
                    // Internet Connection is Present
                    // make HTTP requests
                    /*showAlertDialog(this, "Internet Connection",
                            "You have internet connection", true);
                */
                    Log.d("internet connection","u hve internet connection");
                } else {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

                    // Changing message text color
                    snackbar.setActionTextColor(Color.RED);

                    // Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);

                    snackbar.show();
                }
                String name = inputFullName.getText().toString();
                String password = inputPassword.getText().toString();


                if (!name.isEmpty() && !password.isEmpty()) {
                    new CreateNewProduct().execute();

                }else{

                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Please Enter Your Credentials!", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

                    // Changing message text color
                    snackbar.setActionTextColor(Color.RED);

                    // Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);

                    snackbar.show();
                }

                // creating new product in background thread
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(LoginActivity2.this);
            pDialog.setMessage("Validating.....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String name = inputFullName.getText().toString();
            String password = inputPassword.getText().toString();


            if (name.isEmpty() && password.isEmpty()) {


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Please Enter Your Credentials!", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

                // Changing message text color
                snackbar.setActionTextColor(Color.RED);

                // Changing action button text color
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);

                snackbar.show();
            }

            JSONObject json = null;
            try {
                json = jsonParser.makeHttpRequest2(AppConfig.url_create_product,
                        "POST", name, password);
            } catch (IOException e) {
                e.printStackTrace();
            }



            Log.d("json after json parser", String.valueOf(json));

            if(json != null){
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("message", "raipur");
                startActivity(i);

            }

            return null;

        }




        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }

}
