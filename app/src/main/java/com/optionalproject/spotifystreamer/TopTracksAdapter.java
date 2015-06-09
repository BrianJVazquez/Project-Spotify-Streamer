package com.optionalproject.spotifystreamer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TopTracksAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] mTrackName;
    private final String[] mAlbumName;
    private final String[] mAlbumArt;

    public TopTracksAdapter(Activity context, String[] trackName, String[] albumName, String[] albumArt) {
        super(context, R.layout.top_track_list_layout, trackName);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.mTrackName = trackName;
        this.mAlbumName = albumName;
        this.mAlbumArt = albumArt;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = view;

        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.top_track_list_layout, parent, false);
        }

        TextView albumName = (TextView) rowView.findViewById(R.id.album_name_id);
        TextView trackName = (TextView) rowView.findViewById(R.id.track_name_id);
        ImageView albumImage = (ImageView) rowView.findViewById(R.id.album_image_id);

        albumName.setText(mAlbumName[position]);
        trackName.setText(mTrackName[position]);
        if (mAlbumArt[position] == null) {
            Picasso.with(context).load(R.drawable.no_album_art_xxxhdpi).resize(256, 256).into(albumImage);
        } else {
            Picasso.with(context).load(mAlbumArt[position]).resize(256, 256).into(albumImage);
        }

        return rowView;
    }
}