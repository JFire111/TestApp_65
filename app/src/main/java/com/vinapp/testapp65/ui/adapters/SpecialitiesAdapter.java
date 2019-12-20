package com.vinapp.testapp65.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.data.Specialty;

import java.util.ArrayList;

public class SpecialitiesAdapter extends RecyclerView.Adapter<SpecialitiesAdapter.SpecializationsViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Specialty> specialities;

    public SpecialitiesAdapter(Context context, ArrayList<Specialty> specialities) {
        this.specialities = specialities;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SpecializationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.specializations_item, parent, false);
        return new SpecializationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecializationsViewHolder holder, final int position) {
        Specialty specialty = specialities.get(position);
        holder.specNameTextView.setText(specialty.getName());
        holder.specNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("-------------", "Click " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return specialities.size();
    }

    public class SpecializationsViewHolder extends RecyclerView.ViewHolder{
        final TextView specNameTextView;
        public SpecializationsViewHolder(@NonNull View itemView) {
            super(itemView);
            specNameTextView = (TextView) itemView.findViewById(R.id.specNameTextView);
        }


    }
}
