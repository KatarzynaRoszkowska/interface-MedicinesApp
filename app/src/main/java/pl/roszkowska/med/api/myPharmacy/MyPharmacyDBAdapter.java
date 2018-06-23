package pl.roszkowska.med.api.myPharmacy;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * The adapter class provides value transfer and inserts in the appropriate fields
 */

public class MyPharmacyDBAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Medicines medicines;
    private List<MyPharmacyDB> myPharmacyDBList;
    Intent myPharmacyDetailsIntent;
    MedicinesService medicinesService;
    MyPharmacyActivity myPharmacy;
    private List<String> idList;
    private MyMedicinesApplication myMedicinesApplication;
    private MyPharmacyDetailsActivity myPharmacyDetailsActivity;
    private String token;


    public MyPharmacyActivity getMyPharmacy() {
        return myPharmacy;
    }

    public MyPharmacyDBAdapter() {
    }

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

    /**
     * this method passes selected values to the appropriate fields and then placing them into the application
     */

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        idList = myPharmacy.getIdList();
        MyPharmacyViewHolder myPharmacyViewHolder = (MyPharmacyViewHolder) holder;
        final MyPharmacyDB myPharmacyDB = myPharmacyDBList.get(position);

        myPharmacyViewHolder.name.setText(myPharmacyDB.getMedicines().getMedicinesName());
        myPharmacyViewHolder.howMany.setText("Ilość w opakowaniu : " + myPharmacyDB.getHowMany());
        myPharmacyViewHolder.validate.setText("Termin ważności: " + myPharmacyDB.getExpirationData());

        /**
         * if you take medicine then show green checbox in application
         */

        if (myPharmacyDB.getIsTaken() == "true")
            myPharmacyViewHolder.check.setImageResource(R.drawable.ic_check_box_black_24dp);

        /**
         * if you do not take the medicine the show red checbox in application
         */

        if (myPharmacyDB.getIsTaken() == "false" || myPharmacyDB.getIsTaken() == null)
            myPharmacyViewHolder.check.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);

        /**
         * delete selected medicines from myPharmacy
         */

        myPharmacyViewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMedicinesApplication = (MyMedicinesApplication) context.getApplicationContext();
                medicinesService = myMedicinesApplication.getMedicinesService();
                Call<MyPharmacyDB> delete = medicinesService.deleteMyPharmacie(myMedicinesApplication.getToken().getTokenID(), myPharmacyDBList.get(position).getId());
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
                        /**
                         * remove the item from recycler view
                         */
                        removeItem(position);
                        downloadMyMedicines();
                        Log.d("DELETE", "Nie udało się usunąc leku");

                    }
                });
                Toast.makeText(v.getContext(), "LEK USUNIĘTO", Toast.LENGTH_SHORT).show();


            }
        });

        /**
         * edit selected medicine form MyPharmacy and set the values like expirationDate, howMany, isTaken
         */

        myPharmacyViewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myPharmacyDetailsIntent = new Intent(v.getContext(), MyPharmacyDetailsActivity.class);
                myMedicinesApplication = (MyMedicinesApplication) context.getApplicationContext();
                token = myMedicinesApplication.getToken().getTokenID();
                medicinesService = myMedicinesApplication.getMedicinesService();
                String name = myPharmacyDBList.get(position).getMedicines().getMedicinesName();
                String howMany = myPharmacyDBList.get(position).getHowMany();
                String isTaken = myPharmacyDBList.get(position).getIsTaken();
                String expirationDate = myPharmacyDBList.get(position).getExpirationData();
                String finalPosition = myPharmacyDBList.get(position).getId();

                myPharmacyDetailsIntent.putExtra("nazwaLeku", name);
                myPharmacyDetailsIntent.putExtra("expirationDate", expirationDate);
                myPharmacyDetailsIntent.putExtra("howMany", howMany);
                myPharmacyDetailsIntent.putExtra("isTaken", isTaken);
                myPharmacyDetailsIntent.putExtra("id", finalPosition);
                myPharmacyDetailsIntent.putExtra("token", token);

                startActivity(v.getContext(), myPharmacyDetailsIntent, null);

            }
        });

    }

    @Override
    public int getItemCount() {
        return myPharmacyDBList.size();
    }


    /**
     *  method that remove item from RecyclerView
     */

    public void removeItem(int position) {
        myPharmacyDBList.remove(position);
        notifyItemRemoved(position);
    }


    class MyPharmacyViewHolder extends RecyclerView.ViewHolder {

        TextView name, validate, howMany;
        ImageView check;
        protected ImageButton removeButton;
        protected ImageButton editButton;

        public MyPharmacyViewHolder(View itemView) {


            super(itemView);

            name = itemView.findViewById(R.id.name);
            validate = itemView.findViewById(R.id.validate);
            howMany = itemView.findViewById(R.id.howMany);
            removeButton = itemView.findViewById(R.id.info_button);
            editButton = itemView.findViewById(R.id.edit_button);
            check = itemView.findViewById(R.id.imageView3);
        }

    }

    public void setMyPharmacyDBList(List<MyPharmacyDB> myPharmacyDBList) {
        this.myPharmacyDBList = myPharmacyDBList;

        /**
         * refresh RecyclerView
         */

        notifyDataSetChanged();
    }

    public List<MyPharmacyDB> getMyPharmacyDBList() {
        /**
         * refresh RecyclerView
         */

        notifyDataSetChanged();
        return myPharmacyDBList;
    }

    protected void downloadMyMedicines() {
        myMedicinesApplication = (MyMedicinesApplication) context.getApplicationContext();
        medicinesService = myMedicinesApplication.getMedicinesService();
        token = myMedicinesApplication.getToken().getTokenID();
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


}
