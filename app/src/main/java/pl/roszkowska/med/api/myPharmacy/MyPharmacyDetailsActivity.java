package pl.roszkowska.med.api.myPharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import pl.roszkowska.med.R;

public class MyPharmacyDetailsActivity extends AppCompatActivity {

    private TextView MPNameDetails;
    private EditText MPvalidateDate, MPquantity;
    String MPvalidateDate1,MPquantity1;
    private CheckBox MPisTaken;

    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pharmacy_details);

        button = findViewById(R.id.MPDetailsButton);
        MPNameDetails = findViewById(R.id.MPNameDetails);
        MPvalidateDate = findViewById(R.id.MPvalidateDate);
        MPquantity = findViewById(R.id.MPquantity);
        MPisTaken = findViewById(R.id.MPisTaken);

        MPNameDetails.setText(getIntent().getExtras().getString("nazwaLeku"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MPvalidateDate1 = MPvalidateDate.getText().toString();
                MPquantity1 = MPquantity.getText().toString();
                if(MPquantity.getText().toString().trim().length() == 0)
                    MPquantity.setError("Puste pole");
                else if (MPvalidateDate.getText().toString().trim().length() == 0)
                    MPvalidateDate.setError("Puste pole");
                else
                    {

                    if(MPisTaken.isChecked()) {
                        //TODO MACIEJ ZROB CALL DO SERIWSU DO TABELI MYPHARMACY I ZAPISZ REKORD Z POWYZSZYCH POL i NAZWY LEKU-- isTaken zrob na tak
                    }
                    else
                    {
                        //TODO MACIEJ ZROB CALL DO SERIWSU DO TABELI MYPHARMACY I ZAPISZ REKORD Z POWYZSZYCH POL i NAZWY LEKU-- isTaken zrob na nie
                    }

                    Intent intent = new Intent(MyPharmacyDetailsActivity.this, MyPharmacyActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}
