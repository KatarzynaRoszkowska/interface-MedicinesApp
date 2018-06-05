package pl.roszkowska.med.api.medicines;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pl.roszkowska.med.R;

public class ShowDetailsMedicinesActivity extends AppCompatActivity {

    private TextView detailsMedicinesName, detailsComposition, detailsFormOfTheDrag, detailsCategory, detailsSpeciality,
            detailsActivity, detailsIndications, detailsWayOfGiving, detailsPossibleSideEffect, detailsDose, detailsIsPrescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_medicines);



        detailsMedicinesName = findViewById(R.id.detailsMedicinesName);
        detailsComposition = findViewById(R.id.detailsComposition);
        detailsFormOfTheDrag = findViewById(R.id.detailsFormOfTheDrag);
        detailsCategory = findViewById(R.id.detailsCategory);
        detailsSpeciality = findViewById(R.id.detailsSpeciality);
        detailsActivity = findViewById(R.id.detailsActivity);
        detailsIndications = findViewById(R.id.detailsIndications);
        detailsWayOfGiving = findViewById(R.id.detailsWayOfGiving);
        detailsPossibleSideEffect = findViewById(R.id.detailsPossibleSideEffect);
        detailsDose = findViewById(R.id.detailsDose);
        detailsIsPrescription = findViewById(R.id.detailsIsPrescription);

        detailsMedicinesName.setText(getIntent().getExtras().getString("medicinesName"));
        detailsComposition.setText("Skład: "+ getIntent().getExtras().getString("composition"));
        detailsFormOfTheDrag.setText("Formuła: "+ getIntent().getExtras().getString("formOfTheDrag"));
        detailsCategory.setText("Kategoria: "+ getIntent().getExtras().getString("category"));
        detailsSpeciality.setText("Przeznaczenie: " + getIntent().getExtras().getString("speciality"));
        detailsActivity.setText("Działanie: "+ getIntent().getExtras().getString("activity"));
        detailsIndications.setText("Wskazania: "+ getIntent().getExtras().getString("indications"));
        detailsWayOfGiving.setText("Sposób zażywania: "+ getIntent().getExtras().getString("wayOfGiving"));
        detailsPossibleSideEffect.setText("Skutki uboczne: " + getIntent().getExtras().getString("possibleSideEffect"));
        detailsDose.setText("Dawka: "+ getIntent().getExtras().getString("dose"));
        detailsIsPrescription.setText("Na receptę?" +getIntent().getExtras().getString("IsPrescription"));
    }
}
