package com.optionalproject.spotifystreamer;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.AlbumsPager;


/**
 * A placeholder fragment containing a simple view.
 */
public class TopTracksMainActivityFragment extends Fragment {

    public final String INTENT_TOP_TRACKS = "top tracks";
    private ListView mTopListView;

    public TopTracksMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        String tempID = null;
        if (arguments != null) {
            tempID = arguments.getString(INTENT_TOP_TRACKS);
        }
        String mSpotifyId = tempID;
        TopTrackTask fetchTopTracks = new TopTrackTask();
        fetchTopTracks.execute(mSpotifyId);
        View rootView = inflater.inflate(R.layout.fragment_top_tracks_main, container, false);
        mTopListView = (ListView) rootView.findViewById(R.id.albums_listview);


        return rootView;
    }

    public class TopTrackTask extends AsyncTask<String, Void, Object> {

        @Override
        protected Object doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }
            SpotifyApi api = new SpotifyApi();
            SpotifyService spotify = api.getService();
            AlbumsPager getAlbums = spotify.searchAlbums(spotify.getArtist(params[0]).name);

            return getAlbums;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            AlbumsPager albumsPager = (AlbumsPager) o;
            int size = albumsPager.albums.items.size();
            String[] tempImage = new String[size];
            String[] tempType = new String[size];
            String[] tempAlbum = new String[size];
            for (int i = 0; i < size; i++) {
                int albumImageSize = albumsPager.albums.items.get(i).images.size();
                if (albumImageSize > 0) {
                    tempImage[i] = albumsPager.albums.items.get(i).images.get(0).url;
                } else {
                    tempImage[i] = null;
                }
                tempType[i] = albumsPager.albums.items.get(i).album_type;
                tempAlbum[i] = albumsPager.albums.items.get(i).name;
            }
            TopTracksAdapter mTopTrackAdapter =
                    new TopTracksAdapter(getActivity(), tempType, tempAlbum, tempImage);
            mTopListView.setAdapter(mTopTrackAdapter);

        }
    }
}
