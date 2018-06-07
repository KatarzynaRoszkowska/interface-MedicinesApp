package pl.roszkowska.med.api.myPharmacy;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.R;


public class MyPharmacyDBAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<MyPharmacyDB> myPharmacyDBList;
    Intent myPharmacyDetailsIntent;


    public MyPharmacyDBAdapter( List<MyPharmacyDB> myPharmacyDBList) {
        this.myPharmacyDBList = myPharmacyDBList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_my_pharmacy_list, parent, false);

        return new MyPharmacyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        MyPharmacyViewHolder myPharmacyViewHolder = (MyPharmacyViewHolder) holder;
        MyPharmacyDB myPharmacyDB = myPharmacyDBList.get(position);

        myPharmacyViewHolder.name.setText(myPharmacyDB.getNazwaLeku());
        myPharmacyViewHolder.validate.setText("Termin ważności: "+ myPharmacyDB.getExpirationData());
        myPharmacyViewHolder.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO MACIEJ TUTAJ USUN LEK Z MYPHARMACY:
                String name = myPharmacyDBList.get(holder.getAdapterPosition()).getNazwaLeku();
                // remove the item from recycler view
                removeItem(holder.getAdapterPosition());

                Toast.makeText(v.getContext(), "LEK USUNIĘTO", Toast.LENGTH_SHORT).show();
            }
        });
        myPharmacyViewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO MACIEJ TUTAJ PRZEKAZ NAZWE LEKU DO NOWEJ AKTYWNOSCI, czyli jako name wez nie getNazwaLeku() a getMedicines()... tak jak to wczzesniej robiles

                Toast.makeText(v.getContext(), "EDIT CLICKED", Toast.LENGTH_SHORT).show();

                myPharmacyDetailsIntent = new Intent(v.getContext(),MyPharmacyDetailsActivity.class);
                String name = myPharmacyDBList.get(holder.getAdapterPosition()).getNazwaLeku();
                myPharmacyDetailsIntent.putExtra("nazwaLeku",name);
                v.getContext().startActivity(myPharmacyDetailsIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return myPharmacyDBList.size();
    }

    public void removeItem(int position) {
        myPharmacyDBList.remove(position);
        notifyItemRemoved(position);
    }


    class MyPharmacyViewHolder extends RecyclerView.ViewHolder{

        TextView name, validate;
        protected ImageButton infoButton;
        protected ImageButton editButton;

        public MyPharmacyViewHolder(View itemView){

            super(itemView);

            name = itemView.findViewById(R.id.name);
            validate = itemView.findViewById(R.id.validate);
            infoButton = itemView.findViewById(R.id.info_button);
            editButton = itemView.findViewById(R.id.edit_button);


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
