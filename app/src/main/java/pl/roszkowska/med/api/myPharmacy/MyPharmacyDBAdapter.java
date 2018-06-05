package pl.roszkowska.med.api.myPharmacy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.R;

public class MyPharmacyDBAdapter extends RecyclerView.Adapter<MyPharmacyDBAdapter.MyPharmacyViewHolder>{

    private Context contex;
    private List<MyPharmacyDB> myPharmacyDBList;

    public MyPharmacyDBAdapter(Context contex, List<MyPharmacyDB> myPharmacyDBList) {
        this.contex = contex;
        this.myPharmacyDBList = myPharmacyDBList;
    }

    @NonNull
    @Override
    public MyPharmacyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(contex);
        View view = layoutInflater.inflate(R.layout.activity_my_pharmacy_list,null);
        MyPharmacyViewHolder holder = new MyPharmacyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPharmacyViewHolder holder, int position) {
        MyPharmacyDB myPharmacyDB = myPharmacyDBList.get(position);

        holder.name.setText(myPharmacyDB.getMedicines().getMedicinesName());
        holder.validate.setText(myPharmacyDB.getExpirationData());
    }

    @Override
    public int getItemCount() {
        return myPharmacyDBList.size();
    }

    class MyPharmacyViewHolder extends RecyclerView.ViewHolder{

        TextView name, validate;

        public MyPharmacyViewHolder(View itemView){

            super(itemView);

            name = itemView.findViewById(R.id.name);
            validate = itemView.findViewById(R.id.validate);

        }

    }

    public void setMyPharmacyDBList(List<MyPharmacyDB> myPharmacyDBList) {
        this.myPharmacyDBList = myPharmacyDBList;

        // odśwież RecyclerView
        notifyDataSetChanged();
    }

    public List<MyPharmacyDB> getMyPharmacyDBList() {
        // odśwież RecyclerView
        notifyDataSetChanged();

        return myPharmacyDBList;
    }
}