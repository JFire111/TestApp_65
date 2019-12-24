package com.vinapp.testapp65.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.data.Worker;
import com.vinapp.testapp65.ui.fragments.WorkerDataFragment;

import java.util.ArrayList;

public class WorkersAdapter extends RecyclerView.Adapter<WorkersAdapter.WorkersViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Worker> workers;
    private FragmentActivity activity;

    public WorkersAdapter(Context context, ArrayList<Worker> workers, FragmentActivity activity) {
        this.workers = workers;
        this.inflater = LayoutInflater.from(context);
        this.activity = activity;
    }

    public class WorkersViewHolder extends RecyclerView.ViewHolder {
        final TextView workerTextView;
        final CardView workerCard;
        public WorkersViewHolder(@NonNull View itemView) {
            super(itemView);
            workerTextView = (TextView) itemView.findViewById(R.id.workerNameTextView);
            workerCard = (CardView) itemView.findViewById(R.id.workerCard);
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
        final Worker worker = workers.get(position);
        holder.workerTextView.setText(worker.getFirstName() + " " + worker.getLastName());
        holder.workerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerDataFragment workerDataFragment = new WorkerDataFragment(worker);
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.mainLayout, workerDataFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }
}
