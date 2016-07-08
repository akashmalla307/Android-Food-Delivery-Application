package com.example.malli.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.malli.myapplication.adapter.CustomListAdapter;
import com.example.malli.myapplication.app.AppController;
import com.example.malli.myapplication.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malli on 2/22/2016.
 */
public class SelectRest  extends Activity{

private static final String TAG = SelectRest.class.getSimpleName();

// Movies json url
private ProgressDialog pDialog;
private List<Movie> movieList = new ArrayList<>();
private ListView listView;
private CustomListAdapter adapter;
//private Toolbar mToolbar;
//private FragmentDrawer drawerFragment;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_rest);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        final String url = "http://192.168.1.4:3000/" +message ;
        Log.d("url",url);
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);
  
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();


  //      mToolbar = (Toolbar) findViewById(R.id.toolbar);

    //    setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        drawerFragment = (FragmentDrawer)
  //              getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
    //    drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
      //  drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
//        displayView(0);

        // changing action bar color
       // getActionBar().setBackgroundDrawable(
        //new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
        new Response.Listener<JSONArray>() {
@Override
public void onResponse(JSONArray response) {
        Log.d(TAG, response.toString());
        hidePDialog();

        // Parsing json
        for (int i = 0; i < response.length(); i++) {
        try {

        JSONObject obj = response.getJSONObject(i);
        Movie movie = new Movie();
        movie.setTitle(obj.getString("title"));
        movie.setThumbnailUrl(obj.getString("image"));
        movie.setRating(((Number) obj.get("itemid"))
        .doubleValue());
        //movie.setYear(obj.getInt("releaseYear"));

        // Genre is json array
        JSONArray genreArry = obj.getJSONArray("genre");
        ArrayList<String> genre = new ArrayList<String>();
        for (int j = 0; j < genreArry.length(); j++) {
        genre.add((String) genreArry.get(j));
        }
        movie.setGenre(genre);

        // adding movie to movies array
        movieList.add(movie);

        } catch (JSONException e) {
        e.printStackTrace();
        }

        }

        // notifying list adapter about data changes
        // so that it renders the list view with updated data
        adapter.notifyDataSetChanged();
        }
        }, new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {
        VolleyLog.d(TAG, "Error: " + error.getMessage());
        hidePDialog();

        }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
        }

@Override
public void onDestroy() {
        super.onDestroy();
        hidePDialog();
        }

private void hidePDialog() {
        if (pDialog != null) {
        pDialog.dismiss();
        pDialog = null;
        }
        }
/*
@Override
public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return true;
        }
*/
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
        }

  /*      @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                        return true;
                }

                if(id == R.id.action_search){
                        Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return super.onOptionsItemSelected(item);
        }


    @Override
        public void onDrawerItemSelected(View view, int position) {
                displayView(position);
        }

        private void displayView(int position) {
                Fragment fragment = null;
                String title = getString(R.string.app_name);
                switch (position) {
                        case 0:
                                fragment = new HomeFragment();
                                title = getString(R.string.title_home);
                                break;
                        case 1:
                                fragment = new FriendsFragment();
                                title = getString(R.string.title_friends);
                                break;
                        case 2:
                                fragment = new MessagesFragment();
                                title = getString(R.string.title_messages);
                                break;
                        default:
                                break;
                }

                if (fragment != null) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();

                        // set the toolbar title
                        getSupportActionBar().setTitle(title);
                }
        }
        */
}
