package com.example.malli.myapplication.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.malli.myapplication.R;
import com.example.malli.myapplication.app.AppController;
import com.example.malli.myapplication.model.Movie;

import java.util.List;

/**
 * Created by malli on 2/22/2016.
 */
public class CustomListAdapter4 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<com.example.malli.myapplication.model.Movie> movieItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter4(Activity activity, List<Movie> movieItems) {
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
            convertView = inflater.inflate(R.layout.list_row2, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
       // TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
        Button addbutton = (Button) convertView.findViewById(R.id.addbutton);
        TextView qty = (TextView) convertView.findViewById(R.id.qty);
        Button deletebutton = (Button) convertView.findViewById(R.id.deletebutton);


        final Movie m = movieItems.get(position);
        // final DataModel dataModel = listArray.get(index);
       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("user",m.getTitle());

               /* Intent intent = new Intent(this, SelectMenu.class);
                intent.putExtra("message", m.getTitle());
                startActivity(intent);
               */
             /*   Intent intent=new Intent(view.getContext(),SelectMenu.class);
                intent.putExtra("restname", m.getTitle());
                view.getContext().startActivity(intent);

            }
        });*/
        // getting movie data for the row
        //Movie m = movieItems.get(position);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //        x++;
            }
        });
        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(m.getTitle());

        // rating
        rating.setText("Rating: " + String.valueOf(m.getRating()));

        // genre
        String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);

        // release year
       // year.setText(String.valueOf(m.getYear()));

        return convertView;
    }
}
