package pl.roszkowska.med.api.myPharmacy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.MainActivity;
import pl.roszkowska.med.MyMedicinesApplication;
import pl.roszkowska.med.R;
import pl.roszkowska.med.TokenCredentials;
import pl.roszkowska.med.api.service.MedicinesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPharmacy extends AppCompatActivity {

    RecyclerView recyclerView;
    MyPharmacyDBAdapter myPharmacyDBAdapter;

    List<MyPharmacyDB> myPharmacyDBList;
    private MedicinesService medicinesService;
    private TokenCredentials token;
    private MyMedicinesApplication myMedicinesApplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pharmacy);

        myPharmacyDBList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myPharmacyDBAdapter = new MyPharmacyDBAdapter(this,myPharmacyDBList);
        recyclerView.setAdapter(myPharmacyDBAdapter);

        downloadMyMedicines();
    }

    private void downloadMyMedicines() {
        myMedicinesApplication= (MyMedicinesApplication)getApplication();
        medicinesService = myMedicinesApplication.getMedicinesService();

        Call<List<MyPharmacyDB>> repo = medicinesService.getMyPharmacy(myMedicinesApplication.getToken().getTokenID());

        repo.enqueue(new Callback<List<MyPharmacyDB>>() {
            @Override
            public void onResponse(Call<List<MyPharmacyDB>> call, Response<List<MyPharmacyDB>> response) {
                if(response.isSuccessful()) {
                    for(int i=0; i<response.body().size();i++) {
                        Log.d("TAG", response.body().get(i).toString());
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
