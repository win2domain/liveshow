package com.win2domain;

import android.nfc.Tag;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.util.Log;


public class SettingsActivity extends PreferenceActivity {
    private static final String TAG = "[SettingsActivity]";
    EditTextPreference imsi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.settingsactivity);
        imsi = (EditTextPreference) findPreference("IMSI_KEY");
        imsi.getEditText().addTextChangedListener(new SettingTextWatcher(SettingsActivity.this, imsi, 0, 200));
        imsi.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.d(TAG,"[onPreferenceChange] newValue = "+newValue.toString());
                imsi.setSummary(newValue.toString());
                imsi.setText(newValue.toString());
                return true;
            }
        });

    }


}

