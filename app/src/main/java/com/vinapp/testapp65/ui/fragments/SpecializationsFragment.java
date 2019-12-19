package com.vinapp.testapp65.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.Specialization;
import com.vinapp.testapp65.ui.adapters.SpecializationsAdapter;

import java.util.ArrayList;

public class SpecializationsFragment extends Fragment {

    private ArrayList<Specialization> specializations = new ArrayList<>();

    private RecyclerView recyclerView;
    private SpecializationsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specializations, container, false);

        Specialization spec1 = new Specialization();
        spec1.setName("spec 1");
        specializations.add(spec1);
        Specialization spec2 = new Specialization();
        spec2.setName("spec 2");
        specializations.add(spec2);
        Specialization spec3 = new Specialization();
        spec3.setName("spec 3");
        specializations.add(spec3);

        recyclerView = view.findViewById(R.id.specializationsRecyclerView);
        adapter = new SpecializationsAdapter(getContext(), specializations);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
