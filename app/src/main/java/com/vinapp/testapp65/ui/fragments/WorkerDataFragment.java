package com.vinapp.testapp65.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.data.Worker;

public class WorkerDataFragment extends Fragment {

    private Worker worker;
    private TextView nameTextView;
    private TextView birthdayTextView;
    private TextView specialtyTextView;
    private TextView ageTextView;
    private ImageView avatar;

    public WorkerDataFragment(Worker worker) {
        this.worker = worker;
        this.setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_worker_data, container, false);

        nameTextView = view.findViewById(R.id.workerItemNameTextView);
        birthdayTextView = view.findViewById(R.id.workerBirthdayTextView);
        specialtyTextView = view.findViewById(R.id.workerSpecialtyTextView);
        ageTextView = view.findViewById(R.id.workerAgeTextView);
        avatar = view.findViewById(R.id.workerAvatarImageView);

        nameTextView.setText(worker.getFirstName() + " " + worker.getLastName());
        birthdayTextView.setText(worker.getBirthday());
        specialtyTextView.setText(worker.getSpecialty().getName());
        if (worker.getAge() != null) {
            ageTextView.setText(String.valueOf(worker.getAge()));
        } else {
            ageTextView.setText("-");
        }

        try {
            if (worker.getAvatarUrl().toLowerCase().trim().substring(0, 4).equals("http")) {
                Picasso.get().load(worker.getAvatarUrl()).into(avatar);
            } else {
                avatar.setImageResource(R.drawable.no_photo_img);
            }
        } catch (Exception exc) {
            avatar.setImageResource(R.drawable.no_photo_img);
        }

        return view;
    }
}
