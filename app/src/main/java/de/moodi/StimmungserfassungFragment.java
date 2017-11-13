package de.moodi;

/**
 * Created by Daniel on 03.11.2017.
 *
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.moodi.model.Mood;

public class StimmungserfassungFragment extends Fragment {
    //storage for moods sent without Network connection
    List<Mood> storedMoods = new ArrayList<>();

    private static final String TAG = "StimmungserfassungFragment";
    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    boolean mDownloading = false;
    // Keep a reference to the NetworkFragment which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment mNetworkFragment;

    public StimmungserfassungFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");

        NetworkChangeReceiver myReceiver = new NetworkChangeReceiver();
        getContext().registerReceiver(myReceiver, filter);

        mNetworkFragment = NetworkFragment.getInstance(getActivity().getSupportFragmentManager(), "https://moodibackend.herokuapp.com/mood");

        //Toast.makeText(getActivity(), "StimmungserfassungsView geladen!", Toast.LENGTH_SHORT).show();
        View rootView = inflater.inflate(R.layout.fragment_stimmungserfassung, container, false);
        Button okButton = (Button) rootView.findViewById(R.id.button_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Auslesen der UserID aus den SharedPreferences
                SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String prefUserIdKey = getString(R.string.userId_settings_key);
                String prefUserIdDefault = getString(R.string.userId_settings_default);
                String userId = sPrefs.getString(prefUserIdKey,prefUserIdDefault);

                // Auslesen der TeamID aus den SharedPreferences
                String prefTeamIdKey = getString(R.string.teamId_settings_key);
                String prefTeamIdDefault = getString(R.string.teamId_settings_default);
                String teamId = sPrefs.getString(prefTeamIdKey,prefTeamIdDefault);

                SeekBar seekbar_stimmung = (SeekBar) v.getRootView().findViewById(R.id.seekBar_stimmung);
                Mood mood = new Mood(seekbar_stimmung.getProgress(), userId, teamId);
                if(isOnline()){
                    startDownload(mood);
                    Toast.makeText(getActivity(), "Stimmung mit " + seekbar_stimmung.getProgress() + " Punkten Erfasst!", Toast.LENGTH_SHORT).show();

                }else{
                    storedMoods.add(mood);
                    Toast.makeText(getActivity(), "Stimmung mit " + seekbar_stimmung.getProgress() + " Punkten Erfasst! Wird übertragen sobald eine Netzwerkverbindung besteht.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return rootView;
    }

    private void startDownload(Mood mood) {
//        System.out.println("!mDownloading: " + String.valueOf(!mDownloading));
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment.startDownload(mood);
            mDownloading = true;
        }
    }

    public void finishDownloading() {
        mDownloading = false;
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            if (isOnline()) {
                for(Mood storedMood:storedMoods){
                    startDownload(storedMood);
                    Toast.makeText(getActivity(), "Stimmung vom" + new Date(storedMood.getTimestamp()).toString() + " übertragen", Toast.LENGTH_SHORT).show();
                }
                storedMoods.clear();
            }
        }
    }
}
