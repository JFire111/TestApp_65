package com.vinapp.testapp65.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.DataLoader;
import com.vinapp.testapp65.logic.DatabaseManager;
import com.vinapp.testapp65.logic.data.Specialty;
import com.vinapp.testapp65.logic.data.Worker;
import com.vinapp.testapp65.ui.fragments.SpecialitiesFragment;
import com.vinapp.testapp65.ui.fragments.WorkerDataFragment;
import com.vinapp.testapp65.ui.fragments.WorkersListFragment;
import com.vinapp.testapp65.ui.fragments.interfaces.OnSpecialitiesFragmentListener;
import com.vinapp.testapp65.ui.fragments.interfaces.OnWorkersListFragmentListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnSpecialitiesFragmentListener, OnWorkersListFragmentListener {

    private SpecialitiesFragment specialitiesFragment;
    private FragmentManager fragmentManager;
    private WorkersListFragment workersListFragment;
    private WorkerDataFragment workerDataFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            DataLoader dataLoader = new DataLoader(this);
            dataLoader.execute();
            try {
                dataLoader.get();
                specialitiesFragment = new SpecialitiesFragment();
                fragmentManager.beginTransaction().add(R.id.mainLayout, specialitiesFragment).commit();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    @Override
    public void openWorkersListFragment(Specialty specialty) {
        workersListFragment = new WorkersListFragment(specialty.getName());
        fragmentManager.beginTransaction().replace(R.id.mainLayout, workersListFragment).addToBackStack(null).commit();
    }

    @Override
    public void openWorkerDataFragment(Worker worker) {
        workerDataFragment = new WorkerDataFragment(worker);
        fragmentManager.beginTransaction().replace(R.id.mainLayout, workerDataFragment).addToBackStack(null).commit();

    }
}
