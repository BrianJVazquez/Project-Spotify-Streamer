package com.optionalproject.spotifystreamer;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.artist_listview);
        final EditText editText = (EditText)rootView.findViewById(R.id.artist_search_text);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(getActivity(), v.getText(), Toast.LENGTH_SHORT).show();
                    InputMethodManager inputManager
                            = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    handled = true;

                    // search pressed and perform your functionality.
                }

                return handled;
            }
        });

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