package com.example.malli.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.malli.myapplication.adapter.CartCustomListAdapter;
import com.example.malli.myapplication.helper.SQLiteHandler;
import com.example.malli.myapplication.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by malli on 3/20/2016.
 */
public class CartActivity extends AppCompatActivity {
    private Toolbar toolbar;
    TextView totalcost;
    TextView grandtotal;
    Button ConfirmOrder;
//    private TabLayout tabLayout;

    private List<Movie> movieList = new ArrayList<>();
    private ListView listView;
    private CartCustomListAdapter adapter;
    private SQLiteHandler db;
    HashMap<String,String> response1 = new HashMap<String,String>();
    List<String> response;
    String response2;
    JSONArray response3;
    Integer tc;
    //TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        totalcost = (TextView)findViewById(R.id.totalcost);
        grandtotal = (TextView)findViewById(R.id.grandtotal);
        setSupportActionBar(toolbar);
        //get SupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Cart");

        listView = (ListView) findViewById(R.id.list);
        adapter = new CartCustomListAdapter(this, movieList);
        listView.setAdapter(adapter);
      // tv = (TextView)findViewById(R.id.enq);
        db = new SQLiteHandler(getApplicationContext());
      //  response = db.getUserDetails();
        try {
            response3 = db.getUserDetails2();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("response",String.valueOf(response3));

       // Log.d("response",String.valueOf(response3.get(0)));

     //   Log.d("response2",response2);
        tc = 0;
        for (int i = 0; i < response3.length() ; i++) {
            try {

                JSONObject obj = response3.getJSONObject(i);
                Movie movie = new Movie();
                movie.setTitle(obj.getString("name"));
                //movie.setThumbnailUrl(obj.getString("qty"));
                movie.setRating(obj.getInt("cost"));
               tc = tc + Integer.valueOf(obj.getInt("cost"))* Integer.valueOf(obj.getInt("qty"));
                movie.setqty(obj.getString("qty"));

                // adding movie to movies array
                movieList.add(movie);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        totalcost.setText("\u20B9" + String.valueOf(tc));
        tc = tc+100;
        grandtotal.setText("\u20B9" + String.valueOf(tc)+" ");

        // notifying list adapter about data changes
        // so that it renders the list view with updated data
        adapter.notifyDataSetChanged();

        ConfirmOrder = (Button) findViewById(R.id.ConfirmOrder);
        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        ConfirmOrderPage.class);
                startActivity(i);

            }
        });
        /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(view.getContext(), ScrollableTabsActivity.class);
                intent.putExtra("restname", m.getTitle());
                view.getContext().startActivity(intent);

            }
        });*/
        // Adding request to request queue
      //  AppController.getInstance().addToRequestQueue(movieReq);


    }
}
/*<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:layout_scrollFlags="scroll|enterAlways"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light" />

    </android.support.design.widget.AppBarLayout>

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:weightSum="2">

        <ListView
            android:id="@+id/list"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            android:layout_marginTop="100dp"/>


        <TableLayout
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_below="@id/list"
            android:layout_weight="1">

            <TableRow
                android:id="@+id/tableRow1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"

                >

                <TextView
                    android:id="@+id/textView1"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Total Items"
                    />
                <TextView
                    android:id="@+id/textView11"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="idvalue_text"
                    android:gravity="right"

                 />

            </TableRow>
            <TableRow
                android:id="@+id/tableRow2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
               >

                <TextView
                    android:id="@+id/textView2"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Total Cost"
                   />

                <TextView
                    android:id="@+id/textView21"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="idvalue_text"
                  />

            </TableRow>
        </TableLayout>

    </LinearLayout>

</RelativeLayout>
    */