package com.example.androidbasedmedicationalarm_reminderapplication.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidbasedmedicationalarm_reminderapplication.R;
import com.example.androidbasedmedicationalarm_reminderapplication.model.Medicine;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class medicineHolder extends RecyclerView.Adapter<medicineHolder.ViewMedHolder>{
Context context;
List<Medicine> medicineList;

    public medicineHolder(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewMedHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
       context = viewGroup.getContext();
       View view= LayoutInflater.from(context).inflate(R.layout.med_row,viewGroup,false);
        return new ViewMedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewMedHolder viewMedHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    class ViewMedHolder extends RecyclerView.ViewHolder {
        TextView patientName;
        TextView diseaseName;
        TextView medicineName;
        TextView medicineType;
        TextView dose;
        TextView date;
        TextView time;
        TextView prescriptionPlan;
        public ViewMedHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patientName);
            diseaseName = itemView.findViewById(R.id.diseaseName);
            medicineName = itemView.findViewById(R.id.medicineName);
            medicineType = itemView.findViewById(R.id.medicineType);
            dose = itemView.findViewById(R.id.dose);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            prescriptionPlan = itemView.findViewById(R.id.prescriptionPlan);
        }
    }
}
