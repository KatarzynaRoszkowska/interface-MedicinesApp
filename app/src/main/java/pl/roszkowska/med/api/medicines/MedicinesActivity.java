package pl.roszkowska.med.api.medicines;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.MyMedicinesApplication;
import pl.roszkowska.med.R;
import pl.roszkowska.med.api.myPharmacy.MyPharmacyDB;
import pl.roszkowska.med.api.myPharmacy.MyPharmacyDBAdapter;
import pl.roszkowska.med.api.service.MedicinesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicinesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyPharmacyDBAdapter myPharmacyDBAdapter;
    MedicinesAdapter medicinesAdapter;
    List<Medicines> medicinesList;
    List<MyPharmacyDB> myPharmacyDBList;
    private MyMedicinesApplication myMedicinesApplication;
    private MedicinesService medicinesService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        medicinesList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewListOfMedicines);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* medicinesList.add(new Medicines("Apap","na","nanana","nanan"));
        medicinesList.add(new Medicines("Etopiryna","na","nanana","nanan"));
        medicinesList.add(new Medicines("Gripex","na","nanana","nanan"));
        medicinesList.add(new Medicines("NeomagFORTE","na","nanana","nanan"));
        medicinesList.add(new Medicines("Witamina C","na","nanana","nanan"));
        medicinesList.add(new Medicines("lekX","na","nanana","nanan"));*/

        medicinesAdapter = new MedicinesAdapter(this,medicinesList);
        recyclerView.setAdapter(medicinesAdapter);


        ItemTouchHelper.SimpleCallback item = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                medicinesAdapter.notifyItemRemoved(position);
                //medicinesList.remove(position);
                medicinesList.get(position).getMedicinesName();
               // myPharmacyDBList.add(new MyPharmacyDB(medicinesList.get(position).getMedicinesName()));
                //myPharmacyDBAdapter = new MyPharmacyDBAdapter(this,myPharmacyDBList);

                //todo Maciek W tej metodzie swipe pobiera się konretną nazwę leku i teraz
                //wypadało by zapisac ja do tabeli MyPharamcy i tyle, ja chcialam na liscie zrobic, ale tu nie mozna adaptera wywolac innego,
                //wiec postaraj sie wlasnie tutaj tylko na podstawie pobranej danej zapisac to bazy dnych MyPharamacy

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(item);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        downloadMedicines();
    }

    private void downloadMedicines() {
        myMedicinesApplication = (MyMedicinesApplication)getApplication();
        medicinesService = myMedicinesApplication.getMedicinesService();

        Call<List<Medicines>> repo = medicinesService.getMedicines(myMedicinesApplication.getToken().getTokenID());

        repo.enqueue(new Callback<List<Medicines>>() {
            @Override
            public void onResponse(Call<List<Medicines>> call, Response<List<Medicines>> response) {
                for(int i=0; i<response.body().size();i++) {
                    Log.d("TAG", response.body().get(i).toString());
                }
                medicinesAdapter.setMedicinesList(response.body());

            }

            @Override
            public void onFailure(Call<List<Medicines>> call, Throwable t) {

            }
        });

    }
}
