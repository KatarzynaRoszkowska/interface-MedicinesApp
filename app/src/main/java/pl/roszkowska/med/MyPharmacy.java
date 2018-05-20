package pl.roszkowska.med;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyPharmacy extends AppCompatActivity {

    RecyclerView recyclerView;
    MyPharmacyDBAdapter myPharmacyDBAdapter;

    List<MyPharmacyDB> myPharmacyDBList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pharmacy);

        myPharmacyDBList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myPharmacyDBList.add(new MyPharmacyDB("Apap",true, "Data ważności: 06/07/2023",20));
        myPharmacyDBList.add(new MyPharmacyDB("Witamina C",false, "Data ważności: 01/06/2023",30));
        myPharmacyDBList.add(new MyPharmacyDB("Etopiryna",false, "Data ważności: 08/01/2008",5));
        myPharmacyDBList.add(new MyPharmacyDB("Aspiryna",true, "Data ważności: 01/04/20019",10));
        myPharmacyDBList.add(new MyPharmacyDB("ApapMAX",true, "Data ważności: 01/11/2028",45));
        myPharmacyDBList.add(new MyPharmacyDB("ApapFORTE",false, "Data ważności: 11/11/2020",34));

        myPharmacyDBAdapter = new MyPharmacyDBAdapter(this,myPharmacyDBList);
        recyclerView.setAdapter(myPharmacyDBAdapter);
    }
}
