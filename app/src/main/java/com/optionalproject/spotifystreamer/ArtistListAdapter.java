package com.optionalproject.spotifystreamer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ArtistListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] artistName;

    public ArtistListAdapter(Activity context, String[] artistName) {
        super(context, R.layout.result_list_item, artistName);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.artistName = artistName;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.result_list_item, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.name_artist_textview);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image_list_result);

        txtTitle.setText(artistName[position]);
        Picasso.with(context).load(R.drawable.greenday_xxxhdpi).into(imageView);

        return rowView;
    }
}