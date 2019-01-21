package es.iessaladillo.pedrojoya.pr08.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import es.iessaladillo.pedrojoya.pr08.R;
import es.iessaladillo.pedrojoya.pr08.ui.fragment.Lorem_Fragment;
import es.iessaladillo.pedrojoya.pr08.utils.FragmentUtils;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.listFragmentFL,
                    new Lorem_Fragment(), Lorem_Fragment.class.getSimpleName());
        }
    }
}
