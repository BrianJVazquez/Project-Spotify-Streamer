package com.optionalproject.spotifystreamer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ListFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ListFragment extends Fragment {

        ArrayAdapter<String> mArtistAdapter;

        public ListFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            // Create some dummy data for the ListView.  Here's a sample weekly forecast
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
                    "Green Day - Bullet in a Bible"
            };
            List<String> weekForecast = new ArrayList<>(Arrays.asList(data));


            // Now that we have some dummy forecast data, create an ArrayAdapter.
            // The ArrayAdapter will take data from a source (like our dummy forecast) and
            // use it to populate the ListView it's attached to.
            mArtistAdapter =
                    new ArrayAdapter<>(
                            getActivity(), // The current context (this activity)
                            R.layout.fragment_main, // The name of the layout ID.
                            R.id.artist_listview, // The ID of the textview to populate.
                            weekForecast);
            return rootView;
        }
    }
}