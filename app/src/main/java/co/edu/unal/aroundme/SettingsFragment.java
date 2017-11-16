package co.edu.unal.aroundme;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragment {

    public static final String RADIO_PREFERENCE_KEY = "radio_setting";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        final EditTextPreference radioPref = (EditTextPreference)
                findPreference(RADIO_PREFERENCE_KEY);
        String prefsString = prefs.getString(RADIO_PREFERENCE_KEY, getResources().getString(R.string.radio_default));
        radioPref.setSummary(prefsString + " KM");
        radioPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                radioPref.setSummary(newValue.toString() + " KM");
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString(RADIO_PREFERENCE_KEY, newValue.toString());
                ed.apply();
                return true;
            }
        });
    }

}
