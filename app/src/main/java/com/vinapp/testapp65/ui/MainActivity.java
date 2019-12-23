package com.vinapp.testapp65.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.DataLoader;
import com.vinapp.testapp65.logic.DatabaseManager;
import com.vinapp.testapp65.logic.data.Worker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String DATA_URL = "https://gitlab.65apps.com/65gb/static/raw/master/testTask.json";
    private ArrayList<Worker> workers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask asyncTask = new AsyncTask() {
            DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);
            @Override
            protected Object doInBackground(Object[] objects) {
                DataLoader dataLoader = new DataLoader();
                dataLoader.loadData(DATA_URL);
                workers = dataLoader.getWorkers();
                for (int i = 0; i < workers.size(); i++) {
                    databaseManager.addWorker(workers.get(i));
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                databaseManager.getAllWorkers();
            }
        };
        asyncTask.execute();
    }
}
