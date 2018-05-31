package pl.roszkowska.med.api.medicines;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.R;

public class MedicinesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MedicinesAdapter medicinesAdapter;
    List<Medicines> medicinesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        medicinesList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewListOfMedicines);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        medicinesList.add(new Medicines("Apap MAX","Wskazania: bóle głowy, bóle stawów,", "Skład: polopiryna, kwas askorbowy, magnez","forma leku: tabletki przewlekane"));
        medicinesList.add(new Medicines("Apap MAX","Wskazania: bóle głowy, bóle stawów,", "Skład: polopiryna, kwas askorbowy, magnez","forma leku: tabletki przewlekane"));
        medicinesList.add(new Medicines("Apap MAX","Wskazania: bóle głowy, bóle stawów,", "Skład: polopiryna, kwas askorbowy, magnez","forma leku: tabletki przewlekane"));
        medicinesList.add(new Medicines("Apap MAX","Wskazania: bóle głowy, bóle stawów,", "Skład: polopiryna, kwas askorbowy, magnez","forma leku: tabletki przewlekane"));
        medicinesList.add(new Medicines("Apap MAX","Wskazania: bóle głowy, bóle stawów,", "Skład: polopiryna, kwas askorbowy, magnez","forma leku: tabletki przewlekane"));

        medicinesAdapter = new MedicinesAdapter(this,medicinesList);
        recyclerView.setAdapter(medicinesAdapter);
    }
}
