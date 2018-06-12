package pl.roszkowska.med.api.myPharmacy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.MyMedicinesApplication;
import pl.roszkowska.med.R;
import pl.roszkowska.med.api.medicines.Medicines;
import pl.roszkowska.med.api.service.MedicinesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyPharmacyDBAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Medicines medicines;
    private List<MyPharmacyDB> myPharmacyDBList;
    Intent myPharmacyDetailsIntent;
    MedicinesService medicinesService;
    MyPharmacyActivity myPharmacy;
    private List<String> idList;
    private MyMedicinesApplication myMedicinesApplication;
    private MyPharmacyDB myPharmacyDB;

    public MyPharmacyDBAdapter(MyPharmacyActivity myPharmacy) {
        this.myPharmacy = myPharmacy;
    }

    public MyPharmacyDBAdapter(Context context, MyPharmacyActivity myPharmacy, List<MyPharmacyDB> myPharmacyDBList) {
        this.myPharmacy = myPharmacy;
        this.context = context;
        this.myPharmacyDBList = myPharmacyDBList;
        myMedicinesApplication = (MyMedicinesApplication) context.getApplicationContext();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_my_pharmacy_list, parent, false);

        return new MyPharmacyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        idList = myPharmacy.getIdList();

        MyPharmacyViewHolder myPharmacyViewHolder = (MyPharmacyViewHolder) holder;
        final MyPharmacyDB myPharmacyDB = myPharmacyDBList.get(position);

       // myPharmacyViewHolder.name.setText(myPharmacyDB.getMedicines().getMedicinesName());
        myPharmacyViewHolder.name.setText(myPharmacyDB.getNazwaLeku());
        myPharmacyViewHolder.howMany.setText("Ilość w opakowaniu : " + myPharmacyDB.getHowMany());
        myPharmacyViewHolder.validate.setText("Termin ważności: " + myPharmacyDB.getExpirationData());
       /* myPharmacyViewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMedicinesApplication = (MyMedicinesApplication) context.getApplicationContext();
                medicinesService = myMedicinesApplication.getMedicinesService();
                Call<MyPharmacyDB> delete = medicinesService.deleteMyPharmacie(myMedicinesApplication.getToken().getTokenID(), myPharmacyDBList.get(position).getId());
//                final int finalPosition = position;
                delete.enqueue(new Callback<MyPharmacyDB>() {
                    @Override
                    public void onResponse(Call<MyPharmacyDB> call, Response<MyPharmacyDB> response) {
                        if (response.isSuccessful()) {
                            notifyItemRemoved(position);
                            notifyDataSetChanged();
                            removeItem(position);
                            downloadMyMedicines();

                        }
                    }

                    @Override
                    public void onFailure(Call<MyPharmacyDB> call, Throwable t) {
                        //
                        //Gdzieś jest błąd najprawdopodobniej z parsowaniem czegos. Wcześniej działało dobrze i teraz tez potrafi usunąć lek z bazy mimo że jest onFailure :(
                        //
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                        removeItem(position);
                        downloadMyMedicines();
                        Log.d("DELETE", "Nie udało się usunąc leku");

                    }
                });
                Toast.makeText(v.getContext(), "LEK USUNIĘTO", Toast.LENGTH_SHORT).show();
                // remove the item from recycler view

            }
        });*/
        myPharmacyViewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "EDIT CLICKED", Toast.LENGTH_SHORT).show();

                myPharmacyDetailsIntent = new Intent(v.getContext(), MyPharmacyDetailsActivity.class);


                //String name = myPharmacyDBList.get(position).getMedicines().getMedicinesName();
                String name = myPharmacyDBList.get(position).getNazwaLeku();
                String howMany = myPharmacyDBList.get(position).getHowMany();
                String isTaken = myPharmacyDBList.get(position).getIsTaken();
                String expirationDate = myPharmacyDBList.get(position).getExpirationData();

                myPharmacyDetailsIntent.putExtra("nazwaLeku", name);
                myPharmacyDetailsIntent.putExtra("expirationDate",expirationDate);
                myPharmacyDetailsIntent.putExtra("howMany", howMany);
                myPharmacyDetailsIntent.putExtra("isTaken", isTaken);
                v.getContext().startActivity(myPharmacyDetailsIntent);

               // downloadMedicinesById(position);
                //downloadMedicinesById(position,isTaken,expirationDate,howMany);

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


    class MyPharmacyViewHolder extends RecyclerView.ViewHolder {

        TextView name, validate, howMany;
        protected ImageButton removeButton;
        protected ImageButton editButton;

        public MyPharmacyViewHolder(View itemView) {


            super(itemView);

            name = itemView.findViewById(R.id.name);
            validate = itemView.findViewById(R.id.validate);
            howMany = itemView.findViewById(R.id.howMany);
            removeButton = itemView.findViewById(R.id.info_button);
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

    protected void downloadMyMedicines() {
        myMedicinesApplication = (MyMedicinesApplication) context.getApplicationContext();
        medicinesService = myMedicinesApplication.getMedicinesService();
        final Call<List<MyPharmacyDB>> repo = medicinesService.getMyPharmacy(myMedicinesApplication.getToken().getTokenID());

        repo.enqueue(new Callback<List<MyPharmacyDB>>() {
            @Override
            public void onResponse(Call<List<MyPharmacyDB>> call, Response<List<MyPharmacyDB>> response) {
                if (response.isSuccessful()) {
                    idList = new ArrayList<>();

                    for (int i = 0; i < response.body().size(); i++) {
                        Log.d("TAG", response.body().get(i).toString());
                        idList.add(response.body().get(i).getId().toString());
                    }
                    setMyPharmacyDBList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MyPharmacyDB>> call, Throwable t) {
                Log.d("ERROR", t.toString());
            }
        });
    }

    protected void updateMyMedicines(final String isTaken, final String expirationDate, String howMany) {
        myMedicinesApplication = (MyMedicinesApplication) context.getApplicationContext();
        medicinesService = myMedicinesApplication.getMedicinesService();

        myPharmacyDB.setIsTaken(isTaken);
        myPharmacyDB.setExpirationData(expirationDate);
        myPharmacyDB.setHowMany(howMany);

        final Call<MyPharmacyDB> repo = medicinesService.updateMyPharmacy(myMedicinesApplication.getToken().getTokenID(), myPharmacyDB);
        repo.enqueue(new Callback<MyPharmacyDB>() {
            @Override
            public void onResponse(Call<MyPharmacyDB> call, Response<MyPharmacyDB> response) {
                if(response.isSuccessful()) {
                    Log.d("TAG", "Zaktualizowano poprawnie");
                }
            }

            @Override
            public void onFailure(Call<MyPharmacyDB> call, Throwable t) {
                    Log.d("ERROR", t.toString());

            }
        });
    }

    protected void downloadMedicinesById(final int position, final String isTaken, final String expirationDate, final String howMany) {
        myMedicinesApplication = (MyMedicinesApplication) context.getApplicationContext();
        medicinesService = myMedicinesApplication.getMedicinesService();
        Call<MyPharmacyDB> repo = medicinesService.getMyMedicinesById(myMedicinesApplication.getToken().getTokenID(), idList.get(position));
        repo.enqueue(new Callback<MyPharmacyDB>() {
            @Override
            public void onResponse(Call<MyPharmacyDB> call, Response<MyPharmacyDB> response) {
                if(response.isSuccessful()) {
                    Log.d("TAG", "Udalo sie");
                }
                myPharmacyDB = response.body();
                updateMyMedicines(isTaken,expirationDate,howMany);
            }

            @Override
            public void onFailure(Call<MyPharmacyDB> call, Throwable t) {
                Log.d("TAG", "Nie udalo sie");

            }
        });
    }
}
