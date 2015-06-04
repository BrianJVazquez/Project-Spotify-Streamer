package com.optionalproject.spotifystreamer;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;

public class ListFragment extends Fragment{


    ListView listView;
    ArtistListAdapter mArtistAdapter;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView)rootView.findViewById(R.id.artist_listview);
        EditText editText = (EditText)rootView.findViewById(R.id.artist_search_text);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ArtistSearchTask searchTask = new ArtistSearchTask();
                    searchTask.execute(v.getText().toString());
                    //Minimize the softkeyboard to view list
                    InputMethodManager inputManager
                            = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    handled = true;
                }
                return handled;
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), ("Clicked on position " + position), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setAdapter(mArtistAdapter);

        return rootView;
    }

        public class ArtistSearchTask extends AsyncTask<String, Void, String[]> {

            @Override
            protected String[] doInBackground(String... params) {

                if (params.length == 0) {
                    return null;
                }

                SpotifyApi api = new SpotifyApi();
                SpotifyService spotify = api.getService();
                ArtistsPager results = spotify.searchArtists(params[0]);

                int resultSize = results.artists.items.size();

                String[] ArtistSearchResult = new String[resultSize];
                for(int i = 0; i < resultSize; i++){
                    ArtistSearchResult[i] = results.artists.items.get(i).name;
                }

                return ArtistSearchResult;
            }

            @Override
            protected void onPostExecute(String[] strings) {
                super.onPostExecute(strings);
                mArtistAdapter = new ArtistListAdapter(getActivity(), strings);
                listView.setAdapter(mArtistAdapter);
            }
        }
    }