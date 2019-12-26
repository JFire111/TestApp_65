package com.vinapp.testapp65.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.DataLoader;
import com.vinapp.testapp65.logic.data.Specialty;
import com.vinapp.testapp65.logic.data.Worker;
import com.vinapp.testapp65.ui.fragments.SpecialitiesFragment;
import com.vinapp.testapp65.ui.fragments.WorkerDataFragment;
import com.vinapp.testapp65.ui.fragments.WorkersListFragment;
import com.vinapp.testapp65.ui.fragments.interfaces.OnSpecialitiesFragmentListener;
import com.vinapp.testapp65.ui.fragments.interfaces.OnWorkersListFragmentListener;

public class MainActivity extends AppCompatActivity implements OnSpecialitiesFragmentListener, OnWorkersListFragmentListener {

    private SpecialitiesFragment specialitiesFragment;
    private FragmentManager fragmentManager;
    private WorkersListFragment workersListFragment;
    private WorkerDataFragment workerDataFragment;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        actionBar = getSupportActionBar();

        if (savedInstanceState == null) {
            DataLoader dataLoader = new DataLoader(this);
            dataLoader.execute();
            try {
                dataLoader.get();
                specialitiesFragment = new SpecialitiesFragment();
                actionBar.setTitle(R.string.title_specialties);
                fragmentManager.beginTransaction().add(R.id.fragmentContainer, specialitiesFragment).commit();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    @Override
    public void openWorkersListFragment(Specialty specialty) {
        workersListFragment = new WorkersListFragment(specialty.getName());
        switch (specialty.getId()) {
            case 101:
                actionBar.setTitle(R.string.title_managers);
                break;
            case 102:
                actionBar.setTitle(R.string.title_developers);
                break;
            default:
                actionBar.setTitle(R.string.title_workers);
                break;
        }
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, workersListFragment).addToBackStack(null).commit();
    }

    @Override
    public void openWorkerDataFragment(Worker worker) {
        workerDataFragment = new WorkerDataFragment(worker);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, workerDataFragment).addToBackStack(null).commit();
        actionBar.hide();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int check = fragmentManager.getBackStackEntryCount();
        switch (check) {
            case 0:
                actionBar.setTitle(R.string.title_specialties);
                break;
            case 1:
                actionBar.show();
                break;
        }
    }
}
