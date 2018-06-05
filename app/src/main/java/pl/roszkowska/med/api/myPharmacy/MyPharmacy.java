package pl.roszkowska.med.api.myPharmacy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.MyMedicinesApplication;
import pl.roszkowska.med.R;
import pl.roszkowska.med.api.service.MedicinesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPharmacy extends AppCompatActivity {

    RecyclerView recyclerView;
    MyPharmacyDBAdapter myPharmacyDBAdapter;

    List<MyPharmacyDB> myPharmacyDBList;
    private MedicinesService medicinesService;
    private MyMedicinesApplication myMedicinesApplication;
    private List<String> idList;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pharmacy);

        idList = new ArrayList<>();
        myPharmacyDBList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myPharmacyDBAdapter = new MyPharmacyDBAdapter(this, myPharmacyDBList);
        recyclerView.setAdapter(myPharmacyDBAdapter);
        myPharmacyDBList = myPharmacyDBAdapter.getMyPharmacyDBList();

        downloadMyMedicines();

        ItemTouchHelper.SimpleCallback item = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                position = viewHolder.getAdapterPosition();

                Call<MyPharmacyDB> delete = medicinesService.deleteMyPharmacie(myMedicinesApplication.getToken().getTokenID(), idList.get(position));
                delete.enqueue(new Callback<MyPharmacyDB>() {
                    @Override
                    public void onResponse(Call<MyPharmacyDB> call, Response<MyPharmacyDB> response) {
                        if (response.isSuccessful()) {
                            //TODO KASIA
                            /*
                            Rozwiazanie tymczasowe. Dziala odswiezanie listy ale wydaje mi sie ze nie w taki sposób powinno to funkcjonowac
                             */
                            myPharmacyDBAdapter.notifyItemRemoved(position);
                            myPharmacyDBAdapter.notifyDataSetChanged();
                            downloadMyMedicines();
                        }
                    }

                    @Override
                    public void onFailure(Call<MyPharmacyDB> call, Throwable t) {
                        //TODO KASIA
                         /*
                        Rozwiazanie tymczasowe. Dziala odswiezanie listy ale wydaje mi sie ze nie w taki sposób powinno to funkcjonowac
                        */

                        //TODO Maciek
                        /*
                        Gdzieś jest błąd najprawdopodobniej z parsowaniem czegos. Wcześniej działało dobrze i teraz tez potrafi usunąć lek z bazy mimo że jest onFailure :(
                         */
                        myPharmacyDBAdapter.notifyItemRemoved(position);
                        myPharmacyDBAdapter.notifyDataSetChanged();
                        downloadMyMedicines();
                        Log.d("DELETE", "Nie udało się ununą leku");

                    }
                });
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(item);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }


    private void downloadMyMedicines() {
        myMedicinesApplication = (MyMedicinesApplication) getApplication();
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
                    myPharmacyDBAdapter.setMyPharmacyDBList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MyPharmacyDB>> call, Throwable t) {
                Log.d("ERROR", t.toString());
            }
        });
    }

}