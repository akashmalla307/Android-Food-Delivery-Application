package com.example.malli.myapplication.adapter;

/**
 * Created by malli on 3/4/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.malli.myapplication.ChargingEvent;
import com.example.malli.myapplication.R;
import com.example.malli.myapplication.app.AppController;
import com.example.malli.myapplication.helper.SQLiteHandler;
import com.example.malli.myapplication.model.Movie;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by malli on 2/22/2016.
 */
public class CustomListAdapterOne extends BaseAdapter {
    private SQLiteHandler db;
    //    HashMap<Integer,Integer> quantity = new HashMap<Integer,Integer>();
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private EventBus bus = EventBus.getDefault();

    public CustomListAdapterOne(Activity activity, List<Movie> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
        Log.d("size", String.valueOf(movieItems.size()));

        db = new SQLiteHandler(activity.getApplicationContext());
    }


    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolderItem viewHolder = null;
        if (inflater == null) {
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row2, null);


            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();

            viewHolder = new ViewHolderItem();
            viewHolder.thumbNail = (NetworkImageView) convertView
                    .findViewById(R.id.thumbnail);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.rating = (TextView) convertView.findViewById(R.id.rating);
           // viewHolder.genre = (TextView) convertView.findViewById(R.id.genre);
            viewHolder.addbutton = (Button) convertView.findViewById(R.id.addbutton);
            viewHolder.qty = (TextView) convertView.findViewById(R.id.qty);
            viewHolder.deletebutton = (Button) convertView.findViewById(R.id.deletebutton);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolderItem) convertView.getTag();

        }
        final Integer uniqueKey = Integer.valueOf(position);
        final Movie m = movieItems.get(position);

        // store the holder with the view.
        //    convertView.setTag(viewHolder);
      /*  for(int j =0;j<movieItems.size();j++){
            if(db.getQty(j) == null)
            {
                viewHolder.qty.setText("0");
            }else {
                Log.d("intial", db.getQty(j));
                viewHolder.qty.setText(db.getQty(j));
            }
        }*/

        if(db.getQty(m.getitemid()) == null){
            viewHolder.qty.setText("0");
        } else {
            viewHolder.qty.setText(db.getQty(m.getitemid()));
        }


        final ViewHolderItem finalViewHolder = viewHolder;

        viewHolder.addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String val = finalViewHolder.qty.getText().toString();
               /* if(val == null)
                val= String.valueOf(1);
                else {
                    val = String.valueOf(Integer.valueOf(val) + 1);
                Log.d("val",String.valueOf(val));
                }*/
                String val;
                if(db.getQty(m.getitemid()) == null) {
                    val = "0";
                    Log.d("val", String.valueOf(Integer.valueOf(val) + 1));
                    Log.d("cost3", String.valueOf(m.getRating1()));
                    db.add(m.getTitle(), String.valueOf(Integer.valueOf(val) + 1), String.valueOf(m.getRating1()), String.valueOf(m.getitemid()));
                    db.addqty(String.valueOf(m.getitemid()), String.valueOf(Integer.valueOf(val) + 1));

                }else{
                    val = db.getQty(m.getitemid());
                    Log.d("val", String.valueOf(Integer.valueOf(val) + 1));
                    db.add(m.getTitle(), String.valueOf(Integer.valueOf(val) + 1), String.valueOf(m.getRating1()), String.valueOf(m.getitemid()));
                    db.addqty(String.valueOf(m.getitemid()), String.valueOf(Integer.valueOf(val) + 1));
                }

                String val1;
                try {
                    val1 = db.getQty(m.getitemid());
                 Log.d("val1", db.getQty(m.getitemid()));
                    m.setqty(val1);
                    Log.d("getqty", m.getqty());
                    finalViewHolder.qty.setText(m.getqty());
                    // finalViewHolder.qty.setText(m.getTotalCost());
                    db.addtotalcost(String.valueOf(Integer.valueOf(val1) * m.getRating1()));
                 }catch(Exception e){
                      Log.e("error",String.valueOf(e));
                  }


                Integer totalcost = db.gettotalcost2();
                Log.d("totalcost",String.valueOf(totalcost));
                EventBus.getDefault().post(new ChargingEvent(totalcost));
            }
        });

        viewHolder.deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String val = finalViewHolder.qty.getText().toString();
               /* if(val == null)
                val= String.valueOf(1);
                else {
                    val = String.valueOf(Integer.valueOf(val) + 1);
                Log.d("val",String.valueOf(val));
                }*/
                String val;
                if(db.getQty(m.getitemid()) == null) {
                    val = "0";
                    Log.d("val", String.valueOf(Integer.valueOf(val)));
                    Log.d("cost3", String.valueOf(m.getRating1()));
                    //db.add(m.getTitle(), String.valueOf(Integer.valueOf(val) + 1), String.valueOf(m.getRating1()), String.valueOf(m.getitemid()));
                    //db.addqty(String.valueOf(m.getitemid()), String.valueOf(Integer.valueOf(val) + 1));

                }else{
                    val = db.getQty(m.getitemid());
                    Log.d("val", String.valueOf(Integer.valueOf(val) - 1));
                    if((Integer.valueOf(val) - 1) == 0){
                        db.add(m.getTitle(), String.valueOf(0), String.valueOf(m.getRating1()), String.valueOf(m.getitemid()));
                        db.addqty(String.valueOf(m.getitemid()), String.valueOf(0));
                    }
                    else{
                    db.add(m.getTitle(), String.valueOf(Integer.valueOf(val) - 1), String.valueOf(m.getRating1()), String.valueOf(m.getitemid()));
                    db.addqty(String.valueOf(m.getitemid()), String.valueOf(Integer.valueOf(val) - 1));
                   }
                }

                String val1;
                try {
                    val1 = db.getQty(m.getitemid());
                    Log.d("val1", db.getQty(m.getitemid()));
                    m.setqty(val1);
                    Log.d("getqty", m.getqty());
                    finalViewHolder.qty.setText(m.getqty());
                    // finalViewHolder.qty.setText(m.getTotalCost());
                    db.deletetotalcost(String.valueOf(m.getRating1()));
                }catch(Exception e){
                    Log.e("error",String.valueOf(e));
                }


                Integer totalcost = db.gettotalcost2();
                Log.d("totalcost",String.valueOf(totalcost));
                EventBus.getDefault().post(new ChargingEvent(totalcost));
            }
        });

        // thumbnail image
        viewHolder.thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        viewHolder.title.setText(m.getTitle());

        // rating
        viewHolder.rating.setText("Cost: " + String.valueOf(m.getRating1()));

        // genre
        /*String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        viewHolder.genre.setText(genreStr);
         */
        // release year
        //year.setText(String.valueOf(m.getYear()));

        return convertView;
    }
}
