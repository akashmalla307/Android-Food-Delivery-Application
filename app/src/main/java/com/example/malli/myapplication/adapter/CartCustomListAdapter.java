package com.example.malli.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.malli.myapplication.R;
import com.example.malli.myapplication.ScrollableTabsActivity;
import com.example.malli.myapplication.app.AppController;
import com.example.malli.myapplication.model.Movie;

import java.util.List;

/**
 * Created by malli on 3/20/2016.
 */
public class CartCustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CartCustomListAdapter(Activity activity, List<Movie> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
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

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row3, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        /*NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        */
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView cost = (TextView) convertView.findViewById(R.id.rating);
        //TextView cost = (TextView) convertView.findViewById(R.id.genre);
        TextView qty = (TextView) convertView.findViewById(R.id.releaseYear);

        final Movie m = movieItems.get(position);
        // final DataModel dataModel = listArray.get(index);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("user", m.getTitle());

               /* Intent intent = new Intent(this, SelectMenu.class);
                intent.putExtra("message", m.getTitle());
                startActivity(intent);
               */
              /*  Intent intent=new Intent(view.getContext(),SelectMenu.class);
                */
                Intent intent=new Intent(view.getContext(), ScrollableTabsActivity.class);
                //intent.putExtra("restname", m.getTitle());
                view.getContext().startActivity(intent);

            }
        });
        // getting movie data for the row
        //Movie m = movieItems.get(position);

        // thumbnail image
        //thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(m.getTitle());

        // rating
        cost.setText("Cost: " + String.valueOf(m.getRating()));

        // genre
       /* String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);
*/
        // release year
        qty.setText("Qty: "+String.valueOf(m.getqty()));

        return convertView;
    }
}
