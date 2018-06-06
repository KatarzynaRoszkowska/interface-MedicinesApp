package pl.roszkowska.med.api.myPharmacy;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.MyMedicinesApplication;
import pl.roszkowska.med.R;
import pl.roszkowska.med.api.medicines.Medicines;
import pl.roszkowska.med.api.service.MedicinesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPharmacy extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    RecyclerView recyclerView;
    MyPharmacyDBAdapter myPharmacyDBAdapter;
    private CoordinatorLayout coordinatorLayout;
    List<MyPharmacyDB> myPharmacyDBList;
    private MedicinesService medicinesService;
    private MyMedicinesApplication myMedicinesApplication;
    private List<String> idList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pharmacy);

        idList = new ArrayList<>();
        myPharmacyDBList = new ArrayList<>();
        coordinatorLayout = findViewById(R.id.coordinator);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myPharmacyDBAdapter = new MyPharmacyDBAdapter(this, myPharmacyDBList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(myPharmacyDBAdapter);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        //TODO MACIEJ to tez będzie nie potrzebne:

        myPharmacyDBList.add(new MyPharmacyDB("yes","3","03.09.2020","lekA"));
        myPharmacyDBList.add(new MyPharmacyDB("yes","3","03.09.2020","lekB"));
        myPharmacyDBList.add(new MyPharmacyDB("yes","3","03.09.2020","lekC"));
        myPharmacyDBList.add(new MyPharmacyDB("yes","3","03.09.2020","lekD"));


        /*downloadMyMedicines();

        ItemTouchHelper.SimpleCallback item = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                  myPharmacyDBAdapter.notifyItemRemoved(position);
                    myPharmacyDBAdapter.notifyDataSetChanged();
                Call<MyPharmacyDB> delete = medicinesService.deleteMyPharmacie(myMedicinesApplication.getToken().getTokenID(), idList.get(position));

                delete.enqueue(new Callback<MyPharmacyDB>() {
                    @Override
                    public void onResponse(Call<MyPharmacyDB> call, Response<MyPharmacyDB> response) {
                        if (response.isSuccessful()) {
                            //TODO KASIA

                            //Rozwiazanie tymczasowe. Dziala odswiezanie listy ale wydaje mi sie ze nie w taki sposób powinno to funkcjonowac

                            myPharmacyDBAdapter.notifyItemRemoved(position);
                            myPharmacyDBAdapter.notifyDataSetChanged();
                            recyclerView.invalidate();
                            recyclerView.setAdapter(myPharmacyDBAdapter);
                            downloadMyMedicines();
                            Log.d("DELETE", "Usunieto lek");
                        }
                    }

                    @Override
                    public void onFailure(Call<MyPharmacyDB> call, Throwable t) {
                        //TODO KASIA

                       // Rozwiazanie tymczasowe. Dziala odswiezanie listy ale wydaje mi sie ze nie w taki sposób powinno to funkcjonowac


                        //TODO Maciek

                        //Gdzieś jest błąd najprawdopodobniej z parsowaniem czegos. Wcześniej działało dobrze i teraz tez potrafi usunąć lek z bazy mimo że jest onFailure :(

                        myPharmacyDBAdapter.notifyItemRemoved(position);
                        myPharmacyDBAdapter.notifyDataSetChanged();
                        recyclerView.invalidate();
                        recyclerView.setAdapter(myPharmacyDBAdapter);
                        downloadMyMedicines();
                        Log.d("DELETE", "Nie udało się ununą leku");

                    }
                });
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(item);
        itemTouchHelper.attachToRecyclerView(recyclerView);*/

    }


   /* private void downloadMyMedicines() {
        myMedicinesApplication = (MyMedicinesApplication) getApplication();
        medicinesService = myMedicinesApplication.getMedicinesService();

        final Call<List<MyPharmacyDB>> repo = medicinesService.getMyPharmacy(myMedicinesApplication.getToken().getTokenID());

        repo.enqueue(new Callback<List<MyPharmacyDB>>() {
            @Override
            public void onResponse(Call<List<MyPharmacyDB>> call, Response<List<MyPharmacyDB>> response) {
                if (response.isSuccessful()) {
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
    }*/

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof MyPharmacyDBAdapter.MyPharmacyViewHolder) {
            // get the removed item name to display it in snack bar

            //TODO MACIEj tutaj odkomentuj poniższą linijke i zakometuj tą jedną niżej z getNazwaLeku();
            //TODO MACIEJ i tutaj muszisz zaaplikowac swoj kod serwerowy
           // String name = myPharmacyDBList.get(viewHolder.getAdapterPosition()).getMedicines().getMedicinesName();
            String name = myPharmacyDBList.get(viewHolder.getAdapterPosition()).getNazwaLeku();
            // remove the item from recycler view
            myPharmacyDBAdapter.removeItem(viewHolder.getAdapterPosition());

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
                        myPharmacyDBAdapter.getMyPharmacyDBList().remove(position);
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
                    myPharmacyDBAdapter.getMyPharmacyDBList().remove(position);
                    downloadMyMedicines();
                    Log.d("DELETE", "Nie udało się ununą leku");

                }
            });
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds cartList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}