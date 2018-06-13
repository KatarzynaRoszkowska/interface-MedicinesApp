package pl.roszkowska.med.api.myPharmacy;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
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

public class MyPharmacyActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyPharmacyDBAdapter myPharmacyDBAdapter;
    List<MyPharmacyDB> myPharmacyDBList;
    private MedicinesService medicinesService;
    private MyMedicinesApplication myMedicinesApplication;
    private MyPharmacyDetailsActivity myPharmacyDetailsActivity;
    private List<String> idList;

    public List<String> getIdList() {
        return idList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pharmacy);


        downloadMyMedicines();

        myPharmacyDBList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myPharmacyDBAdapter = new MyPharmacyDBAdapter(this, this, myPharmacyDBList);


        recyclerView.setAdapter(myPharmacyDBAdapter);

//        myPharmacyDBList.add(new MyPharmacyDB("yes","50","03-05-2010","Apap"));
//        myPharmacyDBList.add(new MyPharmacyDB("no","43","09-11-2017","Witamina C"));
//        myPharmacyDBList.add(new MyPharmacyDB("",null,"","faFSF"));
//        myPharmacyDBList.add(new MyPharmacyDB("no","23","03-05-2010","faFSF"));
//        myPharmacyDBList.add(new MyPharmacyDB("no","45","AsSc","faFSF"));
//        myPharmacyDBList.add(new MyPharmacyDB("yes","3","AsSc","faFSF"));

        myPharmacyDBList = myPharmacyDBAdapter.getMyPharmacyDBList();

    }

    protected void downloadMyMedicines() {
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
