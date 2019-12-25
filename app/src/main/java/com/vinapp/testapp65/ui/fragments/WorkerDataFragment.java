package com.vinapp.testapp65.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.data.Worker;

public class WorkerDataFragment extends Fragment {

    private Worker worker;
    private TextView nameTextView;
    private TextView birthdayTextView;
    private TextView specialtyTextView;
    private TextView ageTextView;

    public WorkerDataFragment(Worker worker) {
        this.worker = worker;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_worker_data, container, false);

        nameTextView = view.findViewById(R.id.workerNameTextView);
        birthdayTextView = view.findViewById(R.id.workerBirthdayTextView);
        specialtyTextView = view.findViewById(R.id.workerSpecialtyTextView);
        ageTextView = view.findViewById(R.id.workerAgeTextView);

        nameTextView.setText(worker.getFirstName() + " " + worker.getLastName());
        birthdayTextView.setText(worker.getBirthday());
        specialtyTextView.setText(worker.getSpecialty().getName());

        return view;
    }
}
