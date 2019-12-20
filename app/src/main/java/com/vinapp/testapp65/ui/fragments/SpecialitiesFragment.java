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

        Specialty spec1 = new Specialty();
        spec1.setName("spec 1");
        specialities.add(spec1);
        Specialty spec2 = new Specialty();
        spec2.setName("spec 2");
        specialities.add(spec2);
        Specialty spec3 = new Specialty();
        spec3.setName("spec 3");
        specialities.add(spec3);

        recyclerView = view.findViewById(R.id.specializationsRecyclerView);
        adapter = new SpecialitiesAdapter(getContext(), specialities);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
