package com.tolsma.ryan.airlinecheckin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;

/**
 * Created by ryan on 1/13/16.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static String TAG = "SettingsFragment";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String idk) {
        addPreferencesFromResource(R.xml.fragment_settings);
        SharedPreferences sp = getPreferenceManager().getSharedPreferences();
       /* String temp;
        if( (temp=sp.getString( getString(R.string.text_number_preference),"" )).equals("")) {
            sp.edit().putString(getString(R.string.text_number_preference), "Email Address");
        }
        if( (temp=sp.getString(getString(R.string.email_address_preference),"")).equals("")) {
            sp.edit().putString(getString(R.string.email_address_preference), "Phone Number");

        }

*/

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CleanupApplication.getAppComponent().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sp, String key) {
        Preference preference = findPreference(key);
        if (preference instanceof EditTextPreference) {
            String temp = "";
            if (sp.getString(key, "").equals("")) {

                if (preference.getKey().equals(getString(R.string.text_number_preference))) {
                    temp = "Phone Number";
                } else if (preference.getKey().equals(getString(R.string.email_address_preference))) {
                    temp = "Email Address";
                }


            } else {
                temp = sp.getString(key, "");
            }
            preference.setTitle(temp);

        }

    }

}