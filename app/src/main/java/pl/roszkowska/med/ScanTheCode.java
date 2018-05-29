package pl.roszkowska.med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import pl.roszkowska.med.R;

public class ScanTheCode extends AppCompatActivity {

    private TextView contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_the_code);

        //TODO:DLA MACIEJA !!!
        /*pobiera kod EAN zeskanowany
         na razie roboczo zeskanowany kod pojawia sie w polu Kod.. ale docelowo chyba go nie potrzebujemy
        tutaj trzeba byloby zrobic:
        get Product from Medicines where id (to ta zmienna na dole) == kod ean w bazie danych
        i pozniej pobrac pozostale wartosci z bazy danych do tego EAN takie jak: nazwa, zastosowaine, recepta etc. i ustawiać je na dole
        np nameOfMedicine.setText();
        */

        //pobranie parametru EAN ze skanowania leku i umieszczenie go na cardView o leku
        contentTxt = findViewById(R.id.activity);
        Bundle b = getIntent().getExtras();
        String id = b.getString("ean");
        contentTxt.setText("Kod EAN" + id);
    }

}
