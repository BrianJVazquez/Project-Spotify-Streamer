package com.optionalproject.spotifystreamer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class ListFragment extends Fragment{

    ArrayAdapter<String> mArtistAdapter;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] data = {
                "Green Day - Dookie",
                "Green Day - American Idiot",
                "Green Day - Warning",
                "Green Day - Uno",
                "Green Day - Nimrod",
                "Green Day - Insomniac",
                "Green Day - Tre",
                "Green Day - 21st Century Breakdown",
                "Green Day - Dos",
                "Green Day - Kerplunk",
                "Green Day - International SuperHits",
                "Green Day - Bullet in a Bible",
                "Green Day - Insomniac",
                "Green Day - Tre",
                "Green Day - 21st Century Breakdown",
                "Green Day - Dos",
                "Green Day - Kerplunk",
                "Green Day - International SuperHits",
                "Green Day - Bullet in a Bible"
        };

        List<String> artistArray = new ArrayList<String>(Arrays.asList(data));

        ArtistListAdapter artistAdapter =
                new ArtistListAdapter(getActivity(), data, R.drawable.greenday_xxxhdpi);

//        mArtistAdapter =
//                new ArrayAdapter<String>(
//                        getActivity(), // The current context (this activity)
//                        R.layout.result_list_item, // The name of the layout ID.
//                        R.id.name_artist_textview, // The ID of the textview to populate.
//                        artistArray);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.artist_listview);

        listView.setAdapter(artistAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), ("Clicked on position " + position), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
    }