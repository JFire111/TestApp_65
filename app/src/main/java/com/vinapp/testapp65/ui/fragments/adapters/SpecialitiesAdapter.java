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
import com.vinapp.testapp65.logic.data.Specialty;
import com.vinapp.testapp65.ui.fragments.interfaces.OnSpecialitiesFragmentListener;

import java.util.ArrayList;

public class SpecialitiesAdapter extends RecyclerView.Adapter<SpecialitiesAdapter.SpecializationsViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Specialty> specialities;

    private OnSpecialitiesFragmentListener fragmentListener;

    public SpecialitiesAdapter(Context context, ArrayList<Specialty> specialities) {
        this.specialities = specialities;
        this.inflater = LayoutInflater.from(context);
        this.fragmentListener = (OnSpecialitiesFragmentListener) context;
    }

    public class SpecializationsViewHolder extends RecyclerView.ViewHolder{
        final TextView specialtyNameTextView;
        final CardView specialtyCard;
        //Инициализируем объект(item) для RecyclerView с инфой о специальности
        public SpecializationsViewHolder(@NonNull View itemView) {
            super(itemView);
            specialtyNameTextView = (TextView) itemView.findViewById(R.id.specialtyNameTextView);
            specialtyCard = (CardView) itemView.findViewById(R.id.specialtyCard);
        }
    }

    @NonNull
    @Override
    public SpecializationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.specializations_item, parent, false);
        return new SpecializationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpecializationsViewHolder holder, final int position) {
        final Specialty specialty = specialities.get(position);
        //Устанавливаем название специальности во множественном числе в соответствии с ID специальности
        switch (specialty.getId()) {
            case 101:
                holder.specialtyNameTextView.setText(R.string.title_managers);
                break;
            case 102:
                holder.specialtyNameTextView.setText(R.string.title_developers);
                break;
            default:
                holder.specialtyNameTextView.setText(R.string.title_workers);
                break;
        }
        //Обрабатываем нажатие по карточке со специальностью
        holder.specialtyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.openWorkersListFragment(specialty);
            }
        });
    }

    @Override
    public int getItemCount() {
        return specialities.size();
    }
}
