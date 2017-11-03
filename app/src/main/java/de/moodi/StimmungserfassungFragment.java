package de.moodi;

/**
 * Created by Daniel on 03.11.2017.
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class StimmungserfassungFragment extends Fragment{

    public StimmungserfassungFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Toast.makeText(getActivity(), "StimmungserfassungsView geladen!", Toast.LENGTH_SHORT).show();
        View rootView = inflater.inflate(R.layout.fragment_stimmungserfassung, container, false);
        Button okButton = (Button) rootView.findViewById(R.id.button_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeekBar seekbar_stimmung = (SeekBar) v.getRootView().findViewById(R.id.seekBar_stimmung);
                Toast.makeText(getActivity(), "Stimmung mit " + seekbar_stimmung.getProgress() + " Punkten Erfasst!", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

}
