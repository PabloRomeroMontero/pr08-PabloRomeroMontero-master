package es.iessaladillo.pedrojoya.pr08.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceFragmentCompat;
import es.iessaladillo.pedrojoya.pr08.R;

public class PreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String key) {
        setPreferencesFromResource(R.xml.preference_settings, key);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = requireActivity().findViewById(R.id.toolbarSettin);
        toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
    }


}
