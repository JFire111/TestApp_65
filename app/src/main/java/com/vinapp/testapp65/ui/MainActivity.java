package com.vinapp.testapp65.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.DataLoader;
import com.vinapp.testapp65.logic.DatabaseManager;
import com.vinapp.testapp65.logic.data.Specialty;
import com.vinapp.testapp65.logic.data.Worker;
import com.vinapp.testapp65.ui.fragments.SpecialitiesFragment;
import com.vinapp.testapp65.ui.fragments.WorkersListFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SpecialitiesFragment specialitiesFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        specialitiesFragment = new SpecialitiesFragment();

        DataLoader dataLoader = new DataLoader(this);
        dataLoader.execute();
        try {
            dataLoader.get();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.mainLayout, specialitiesFragment);
            fragmentTransaction.commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
