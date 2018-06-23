package pl.roszkowska.med.api.medicines;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import pl.roszkowska.med.R;

/**
 * The class displays the details of the drug selected by the user
 */


public class ShowDetailsMedicinesActivity extends AppCompatActivity {

    private TextView detailsMedicinesName, detailsComposition, detailsFormOfTheDrag, detailsCategory, detailsSpeciality,
            detailsActivity, detailsIndications, detailsWayOfGiving, detailsPossibleSideEffect, detailsDose, detailsIsPrescription;

    /**
     * the method that displays activity_show_details_medicines layout
     * @param savedInstanceState
     */
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
        detailsComposition.setText(Html.fromHtml("<b>" + "Skład: " + "</b>" + getIntent().getExtras().getString("composition")));
        detailsFormOfTheDrag.setText(Html.fromHtml("<b>" + "Formuła: " + "</b>" + getIntent().getExtras().getString("formOfTheDrag")));
        detailsCategory.setText(Html.fromHtml("<b>" + "Kategoria: " + "</b>" + getIntent().getExtras().getString("category")));
        detailsSpeciality.setText(Html.fromHtml("<b>" + "Przeznaczenie: " + "</b>" + getIntent().getExtras().getString("speciality")));
        detailsActivity.setText(Html.fromHtml("<b>" + "Działanie: " + "</b>" + getIntent().getExtras().getString("activity")));
        detailsIndications.setText(Html.fromHtml("<b>" + "Wskazania: " + "</b>" + getIntent().getExtras().getString("indications")));
        detailsWayOfGiving.setText(Html.fromHtml("<b>" + "Sposób zażywania: " + "</b>" + getIntent().getExtras().getString("wayOfGiving")));
        detailsPossibleSideEffect.setText(Html.fromHtml("<b>" + "Skutki uboczne: " + "</b>" + getIntent().getExtras().getString("possibleSideEffect")));
        detailsDose.setText(Html.fromHtml("<b>" + "Dawka: " + "</b>" + getIntent().getExtras().getString("dose")));
        detailsIsPrescription.setText(Html.fromHtml("<b>" + "Na receptę: " + "</b>" + getIntent().getExtras().getString("IsPrescription")));
    }
}
