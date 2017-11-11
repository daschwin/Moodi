package de.moodi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;


/**
 * Created by Daniel on 31.10.2017.
 *
 */

public class SettingsActivity extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        Preference userIdPref = findPreference(getString(R.string.userId_settings_key));
        userIdPref.setOnPreferenceChangeListener(this);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String gespeicherteUserId = sharedPrefs.getString(userIdPref.getKey(), "");
        onPreferenceChange(userIdPref, gespeicherteUserId);

        Preference teamIdPref = findPreference(getString(R.string.teamId_settings_key));
        teamIdPref.setOnPreferenceChangeListener(this);
        String gespeicherteTeamId = sharedPrefs.getString(teamIdPref.getKey(), "");
        onPreferenceChange(teamIdPref, gespeicherteTeamId);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        preference.setSummary(value.toString());

        return true;

    }
}