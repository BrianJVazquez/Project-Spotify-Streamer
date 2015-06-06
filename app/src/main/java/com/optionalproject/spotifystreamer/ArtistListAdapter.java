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
    private final String[] artistImageURL;

    public ArtistListAdapter(Activity context, String[] artistName, String[] artistImageURL) {
        super(context, R.layout.result_list_item, artistName);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.artistName = artistName;
        this.artistImageURL = artistImageURL;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = view;

        if(rowView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.result_list_item, parent, false);
        }

        TextView txtTitle = (TextView) rowView.findViewById(R.id.name_artist_textview);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image_list_result);

        txtTitle.setText(artistName[position]);

        if(artistImageURL[position] == null){
            Picasso.with(context).load(R.drawable.no_album_art_xxxhdpi).resize(256,256).into(imageView);
        }
        else{
            Picasso.with(context).load(artistImageURL[position]).resize(256,256).into(imageView);
        }

        return rowView;
    }
}