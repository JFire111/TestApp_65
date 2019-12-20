package com.vinapp.testapp65.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.DatabaseManager;
import com.vinapp.testapp65.logic.data.Worker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Worker worker1 = new Worker();
        worker1.setFirstName("aaa");
        worker1.setLastName("aaaaaa");
        worker1.setSpecialty("spec1");
        worker1.setBirthday("11.11.1111");
        worker1.setAge(1111);

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.addWorker(worker1);
        databaseManager.getAllWorkers();
    }
}
