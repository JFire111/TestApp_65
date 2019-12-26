package com.vinapp.testapp65.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.DatabaseManager;
import com.vinapp.testapp65.logic.data.Worker;
import com.vinapp.testapp65.ui.fragments.adapters.WorkersAdapter;

import java.util.ArrayList;

public class WorkersListFragment extends Fragment {

    private ArrayList<Worker> workers = new ArrayList<>();
    private String specialty;
    private RecyclerView recyclerView;
    private WorkersAdapter adapter;

    public WorkersListFragment(String specialtyName) {
        this.specialty = specialtyName;
        this.setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workers_list, container, false);

        DatabaseManager databaseManager = new DatabaseManager(getContext());
        workers = databaseManager.getWorkersBySpecialty(specialty);

        adapter = new WorkersAdapter(getContext(), workers);
        recyclerView = view.findViewById(R.id.workersRecyclerView);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
