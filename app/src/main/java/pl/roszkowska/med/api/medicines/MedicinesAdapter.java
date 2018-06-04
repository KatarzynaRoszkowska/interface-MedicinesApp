package pl.roszkowska.med.api.medicines;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import java.util.List;
import pl.roszkowska.med.R;

public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.MedicinesViewHolder>{

    private Context contex;
    private List<Medicines> medicinesList;
    private Medicines medicines;

    public OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listner)
    {
        mListener = listner;
    }

    public MedicinesAdapter(Context contex, List<Medicines> medicinesList) {
        this.contex = contex;
        this.medicinesList = medicinesList;
    }

    @NonNull
    @Override
    public MedicinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(contex);
        View view = layoutInflater.inflate(R.layout.activity_medicines_list,null);
        MedicinesViewHolder holder = new MedicinesViewHolder(view, mListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicinesViewHolder holder, int position) {
        medicines = medicinesList.get(position);

        holder.medicinesName.setText(medicines.getMedicinesName());
        holder.speciality.setText(medicines.getSpeciality());
        holder.composition.setText(medicines.getComposition());
        holder.formOfTheDrag.setText(medicines.getFormOfTheDrag());

    }

    @Override
    public int getItemCount() {

        return medicinesList.size();
    }

    class MedicinesViewHolder extends RecyclerView.ViewHolder{
        TextView medicinesName, speciality, composition, formOfTheDrag, ean;

        public MedicinesViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            medicinesName = itemView.findViewById(R.id.nameListOfMedicines);
            speciality = itemView.findViewById(R.id.specialityListOfMedicines);
            composition = itemView.findViewById(R.id.compositionListOfMedicines);
            formOfTheDrag = itemView.findViewById(R.id.formOfTheDragListOfMedicines);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }


                }
            });
        }
    }

    public void setMedicinesList(List<Medicines> medicinesList) {
        this.medicinesList = medicinesList;

        // odśwież RecyclerView
        notifyDataSetChanged();
    }

    public List<Medicines> getMedicinesList() {
        return medicinesList;
    }
}
