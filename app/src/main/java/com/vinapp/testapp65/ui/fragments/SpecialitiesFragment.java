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
import com.vinapp.testapp65.logic.data.Specialty;
import com.vinapp.testapp65.ui.adapters.SpecialitiesAdapter;

import java.util.ArrayList;

public class SpecialitiesFragment extends Fragment {

    private ArrayList<Specialty> specialities = new ArrayList<>();
    private RecyclerView recyclerView;
    private SpecialitiesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specializations, container, false);

        DatabaseManager databaseManager = new DatabaseManager(getContext());
        specialities = databaseManager.getAllSpecialties();

        adapter = new SpecialitiesAdapter(getContext(), specialities, getActivity());
        recyclerView = view.findViewById(R.id.specializationsRecyclerView);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
