package pl.roszkowska.med.api.Scanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.R;
import pl.roszkowska.med.api.medicines.Medicines;
import pl.roszkowska.med.api.myPharmacy.MyPharmacyDBAdapter;

public class ScanTheCode extends AppCompatActivity {
    private TextView contentTxt, composition, formOfTheDrag, quantity, wayOfGiving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_the_code);

        //TODO:DLA MACIEJA
        /*pobiera kod EAN zeskanowany
         na razie roboczo zeskanowany kod pojawia sie w polu Kod.. ale docelowo chyba go nie potrzebujemy
        tutaj trzeba byloby zrobic:
        get Product from Medicines where id (to ta zmienna na dole) == kod ean w bazie danych
        i pozniej pobrac pozostale wartosci z bazy danych do tego EAN takie jak: nazwa, zastosowaine, recepta etc. i ustawiać je na dole
        np nameOfMedicine.setText();
        */

        //pobranie parametru EAN ze skanowania leku i umieszczenie go na cardView o leku
        contentTxt = findViewById(R.id.activity);

        composition = findViewById(R.id.composition);
        formOfTheDrag = findViewById(R.id.formOfTheDrag);
        quantity = findViewById(R.id.quantity);
        wayOfGiving = findViewById(R.id.wayOfGiving);

        Bundle b = getIntent().getExtras();
        String id = b.getString("ean");
        contentTxt.setText("Kod EAN" + id);
        composition.setText("Skład:1 tabletka musująca zawiera substacje czynne: kwas acetylosalicylowy 330mg, kwas askorbowy oraz substacje pomocnicze.");
        formOfTheDrag.setText("Forma leku: Kapsułki musujące");
        quantity.setText("Ilość w opakowaniu: 20");
        wayOfGiving.setText("Sposób stosowania: Tabletkę należy rozpuścić w szklance wody, mleka lub soku owocowego i natychmiast po rozpuszczeniu wypić.");



    }

}
