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

    private final String DATA_URL = "https://gitlab.65apps.com/65gb/static/raw/master/testTask.json";

    private ArrayList<Worker> workers;
    private ArrayList<Specialty> specialties;
    private SpecialitiesFragment specialitiesFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        specialitiesFragment = new SpecialitiesFragment();

        AsyncTask asyncTask = new AsyncTask() {
            DataLoader dataLoader = new DataLoader();
            DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);
            @Override
            protected Object doInBackground(Object[] objects) {
                dataLoader.loadData(DATA_URL);
                workers = dataLoader.getWorkers();
                specialties = dataLoader.getSpecialties();
                for (int i = 0; i < workers.size(); i++) {
                    databaseManager.addWorker(workers.get(i));
                }
                for (int i = 0; i < specialties.size(); i++) {
                    databaseManager.addSpecialty(specialties.get(i));
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                databaseManager.getWorkersBySpecialty("Менеджер");
                databaseManager.getAllSpecialties();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.mainLayout, specialitiesFragment);
                fragmentTransaction.commit();
            }
        };
        asyncTask.execute();
    }
}
