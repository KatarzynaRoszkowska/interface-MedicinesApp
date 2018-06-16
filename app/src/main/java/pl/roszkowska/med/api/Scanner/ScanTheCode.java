package pl.roszkowska.med.api.Scanner;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.MainActivity;
import pl.roszkowska.med.MyMedicinesApplication;
import pl.roszkowska.med.R;
import pl.roszkowska.med.api.medicines.Medicines;
import pl.roszkowska.med.api.medicines.MedicinesActivity;
import pl.roszkowska.med.api.medicines.MedicinesAdapter;
import pl.roszkowska.med.api.service.MedicinesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/// A class that allows you to view the information and the scanned medicine

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

        medicinesList = new ArrayList<>();
        medicinesAdapter = new MedicinesAdapter(this, medicinesList);

        downloadMedicines();

    }

    private void downloadMedicines() {
        myMedicinesApplication = (MyMedicinesApplication) getApplication();
        medicinesService = myMedicinesApplication.getMedicinesService();
        Bundle c = getIntent().getExtras();
        eanCode = c.getString("ean");

        /*
        Nie jest to dopracowany sposób ponieważ na ułamek sekundy pokazywany jest pusty CardView
         */
        if(eanCode == null) {
            Intent intent = NavUtils.getParentActivityIntent(this);
            NavUtils.navigateUpTo(this, intent);
        } else {
            setContentView(R.layout.activity_scan_the_code);
            nameOfMedicine = findViewById(R.id.nameOfMedicine);
            contentTxt = findViewById(R.id.activity);
            composition = findViewById(R.id.composition);
            formOfTheDrag = findViewById(R.id.formOfTheDrag);
            quantity = findViewById(R.id.quantity);
            wayOfGiving = findViewById(R.id.wayOfGiving);

            Call<Medicines> repo = medicinesService.getMedicinesEan(myMedicinesApplication.getToken().getTokenID(), eanCode);
                repo.enqueue(new Callback<Medicines>() {
                    @Override
                    public void onResponse(Call<Medicines> call, Response<Medicines> response) {
                        if(response.isSuccessful()) {
                            Log.d("IDe", response.body().toString());
                            contentTxt.setText(Html.fromHtml("<b>" + "Kod EAN: " + "</b>" + response.body().getEan()));
                            composition.setText(Html.fromHtml("<b>" + "Skład leku: " + "</b>" + response.body().getComposition()));
                            formOfTheDrag.setText(Html.fromHtml("<b>" + "Forma leku: " + "</b>" + response.body().getFormOfTheDrag()));
                            quantity.setText(Html.fromHtml("<b>" + "Ilość w opakowaniu: " + "</b>" + response.body().getQuantity()));
                            wayOfGiving.setText(Html.fromHtml("<b>" + "Sposób stosowania: " + "</b>" + response.body().getWayOfGiving()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Medicines> call, Throwable t) {
                    Log.d("ERROR", t.toString());
                    }
                });
        }

    }

}
