package com.vinapp.testapp65.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.Specialization;
import com.vinapp.testapp65.logic.Worker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Worker> workers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
