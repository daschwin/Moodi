package de.moodi;

/**
 * Created by Daniel on 03.11.2017.
 *
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import de.moodi.model.Mood;

public class StimmungserfassungFragment extends Fragment {
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
            startDownload(mood);
            Toast.makeText(getActivity(), "Stimmung mit " + seekbar_stimmung.getProgress() + " Punkten Erfasst!", Toast.LENGTH_SHORT).show();
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
}
