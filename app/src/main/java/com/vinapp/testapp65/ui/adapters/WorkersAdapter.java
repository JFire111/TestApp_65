package com.vinapp.testapp65.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.data.Worker;

import java.util.ArrayList;

public class WorkersAdapter extends RecyclerView.Adapter<WorkersAdapter.WorkersViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Worker> workers;

    public WorkersAdapter(Context context, ArrayList<Worker> workers) {
        this.workers = workers;
        this.inflater = LayoutInflater.from(context);
    }

    public class WorkersViewHolder extends RecyclerView.ViewHolder {
        final TextView workerTextView;
        public WorkersViewHolder(@NonNull View itemView) {
            super(itemView);
            workerTextView = (TextView) itemView.findViewById(R.id.workerTextView);
        }
    }

    @NonNull
    @Override
    public WorkersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.workers_item, parent, false);
        return new WorkersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkersViewHolder holder, int position) {
        Worker worker = workers.get(position);
        holder.workerTextView.setText(worker.getFirstName() + " " + worker.getLastName());
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }
}
