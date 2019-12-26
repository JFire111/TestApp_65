package com.vinapp.testapp65.ui.fragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vinapp.testapp65.R;
import com.vinapp.testapp65.logic.data.Worker;
import com.vinapp.testapp65.ui.fragments.interfaces.OnWorkersListFragmentListener;

import java.util.ArrayList;

public class WorkersAdapter extends RecyclerView.Adapter<WorkersAdapter.WorkersViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Worker> workers;

    private OnWorkersListFragmentListener fragmentListener;

    public WorkersAdapter(Context context, ArrayList<Worker> workers) {
        this.workers = workers;
        this.inflater = LayoutInflater.from(context);
        this.fragmentListener = (OnWorkersListFragmentListener) context;
    }

    public class WorkersViewHolder extends RecyclerView.ViewHolder {
        final TextView workerTextView;
        final TextView workerAgeTextView;
        final CardView workerCard;
        //Инициализируем объект(item) для RecyclerView с инфой о работнике
        public WorkersViewHolder(@NonNull View itemView) {
            super(itemView);
            workerTextView = (TextView) itemView.findViewById(R.id.workerItemNameTextView);
            workerAgeTextView = (TextView) itemView.findViewById(R.id.workerItemAgeTextView);
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
        //Если возраст работника неизвестен ставим "-"
        if (worker.getAge() != null) {
            holder.workerAgeTextView.setText(String.valueOf(worker.getAge()));
        } else {
            holder.workerAgeTextView.setText("-");
        }
        //Обрабатываем нажатие по карточке с работником
        holder.workerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.openWorkerDataFragment(worker);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }
}
