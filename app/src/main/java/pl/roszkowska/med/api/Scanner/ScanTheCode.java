package pl.roszkowska.med.api.Scanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.MyMedicinesApplication;
import pl.roszkowska.med.R;
import pl.roszkowska.med.api.medicines.Medicines;
import pl.roszkowska.med.api.medicines.MedicinesActivity;
import pl.roszkowska.med.api.medicines.MedicinesAdapter;
import pl.roszkowska.med.api.service.MedicinesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanTheCode extends AppCompatActivity {
    private TextView contentTxt, composition, formOfTheDrag, quantity, wayOfGiving, nameOfMedicine;
    private MyMedicinesApplication myMedicinesApplication;
    private MedicinesService medicinesService;
    String eanCode;
    private MedicinesAdapter medicinesAdapter;
    private List<Medicines> medicinesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_the_code);

        medicinesList = new ArrayList<>();
        medicinesAdapter = new MedicinesAdapter(this, medicinesList);

        nameOfMedicine = findViewById(R.id.nameOfMedicine);
        contentTxt = findViewById(R.id.activity);
        composition = findViewById(R.id.composition);
        formOfTheDrag = findViewById(R.id.formOfTheDrag);
        quantity = findViewById(R.id.quantity);
        wayOfGiving = findViewById(R.id.wayOfGiving);

        downloadMedicines();
    }

    private void downloadMedicines() {
        myMedicinesApplication = (MyMedicinesApplication) getApplication();
        medicinesService = myMedicinesApplication.getMedicinesService();
        Bundle b = getIntent().getExtras();
        eanCode = b.getString("ean");

                Call<Medicines> repo = medicinesService.getMedicinesEan(myMedicinesApplication.getToken().getTokenID(), eanCode);
                repo.enqueue(new Callback<Medicines>() {
                    @Override
                    public void onResponse(Call<Medicines> call, Response<Medicines> response) {
                        if(response.isSuccessful()) {
                            Log.d("IDe", response.body().toString());
                            nameOfMedicine.setText(response.body().getMedicinesName());
                            contentTxt.setText("Kod EAN " + response.body().getEan());
                            composition.setText("Skład leku: " + response.body().getComposition());
                            formOfTheDrag.setText("Forma leku: " + response.body().getFormOfTheDrag());
                            quantity.setText("Ilość w opakowaniu :" + response.body().getQuantity());
                            wayOfGiving.setText("Sposób stosowania: " + response.body().getWayOfGiving());
                        }
                    }

                    @Override
                    public void onFailure(Call<Medicines> call, Throwable t) {
                    Log.d("ERROR", t.toString());
                    }
                });

    }

}
