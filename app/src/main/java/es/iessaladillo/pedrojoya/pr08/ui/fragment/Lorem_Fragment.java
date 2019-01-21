package es.iessaladillo.pedrojoya.pr08.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import es.iessaladillo.pedrojoya.pr08.R;
import es.iessaladillo.pedrojoya.pr08.databinding.LoremFragmentBinding;
import es.iessaladillo.pedrojoya.pr08.utils.FragmentUtils;

public class Lorem_Fragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private LoremFragmentBinding b;
    private SharedPreferences settings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savecInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.lorem_fragment, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        setupToolbar();
        setupFab();
        changeText();
    }

    private void setupFab() {
        b.initFragmentFab.setOnClickListener(v -> FragmentUtils.replaceFragmentAddToBackstack(requireFragmentManager(),
                R.id.listFragmentFL, new DetailFragment(), DetailFragment.class.getSimpleName(), DetailFragment.class.getSimpleName(), FragmentTransaction.TRANSIT_FRAGMENT_FADE));
    }

    private void setupToolbar() {
        b.toolbar.setTitle(R.string.title_toolbar);
        b.toolbar.inflateMenu(R.menu.menu_fragment);
        b.toolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId()==R.id.sharedPreferencesMenu) {
                navigateToSettings();
                return true;
            }
                return false;
        });
    }

    private void navigateToSettings() {
        FragmentUtils.replaceFragmentAddToBackstack(requireFragmentManager(),
                R.id.listFragmentFL, new PreferenceFragment(),
                PreferenceFragment.class.getSimpleName(), PreferenceFragment.class.getSimpleName(), FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sharedPreferencesMenu) {
            navigateToSettings();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (TextUtils.equals(key, getString(R.string.lbltext_prefkey))) {
            changeText();
        }
    }

    private void changeText() {
        b.txtLorem.setText(settings.getString(getString(R.string.lbltext_prefkey), getString(R.string.main_latin_ipsum)));
    }

    @Override
    public void onResume() {
        super.onResume();
        settings.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        settings.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}
