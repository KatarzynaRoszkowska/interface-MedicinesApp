package pl.roszkowska.med.api.myPharmacy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.roszkowska.med.MyMedicinesApplication;
import pl.roszkowska.med.R;
import pl.roszkowska.med.api.service.MedicinesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPharmacyDetailsActivity extends AppCompatActivity {

    private TextView MPNameDetails;
    private EditText MPvalidateDate, MPquantity;
    private Context context;
    String MPvalidateDate1,MPquantity1;
    private CheckBox MPisTaken;
    static MyPharmacyDetailsActivity myPharmacyDetailsActivity;

    Button button;
    private MyMedicinesApplication myMedicinesApplication;
    private MedicinesService medicinesService;
    private MyPharmacyDBAdapter myPharmacyDBAdapter = new MyPharmacyDBAdapter();
    private MyPharmacyDB myPharmacyDB;
    private String MPisTaken1;
    private String token;
    private List<String> idList;
    String position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pharmacy_details);

        button = findViewById(R.id.MPDetailsButton);
        MPNameDetails = findViewById(R.id.MPNameDetails);
        MPvalidateDate = findViewById(R.id.MPvalidateDate);
        MPquantity = findViewById(R.id.MPquantity);
        MPisTaken = findViewById(R.id.MPisTaken);

        position = getIntent().getExtras().getString("id");
        MPNameDetails.setText(getIntent().getExtras().getString("nazwaLeku"));
        MPvalidateDate.setText(getIntent().getExtras().getString("expirationDate"));
        MPquantity.setText(getIntent().getExtras().getString("howMany"));
        String check = getIntent().getExtras().getString("isTaken");
        token = getIntent().getExtras().getString("token");

        downloadMyMedicines();
//TODO KASIA zakomentowalem bo nie dzialalo
//TODO KASIA wyslalem ci zdjecie ustawainia szczegolow i na nim w polu Data waznosci jest: "dd-mm-yyy" a trzeba zrobic "yyyy-mm-dd" bo taki format przyjmuje baza danych
//        if(check.equals("yes"))
//            MPisTaken.setChecked(true);
//        if(check.equals("false") || check.equals(null));
//            MPisTaken.setChecked(false);
//TODO KASIA zle jest cos zrobione z aktywnosciami. Wysylam filmik na FB jak to wyglada (chodzi o cofanie do poprzedniej aktywnosci i odswiezenie listy recycleView z nowymi wartosciami)
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MPvalidateDate1 = MPvalidateDate.getText().toString();
                MPquantity1 = MPquantity.getText().toString();
                MPisTaken1 = MPisTaken.getText().toString();
                if(MPquantity.getText().toString().trim().length() == 0)
                    MPquantity.setError("Puste pole");
                else if (MPvalidateDate.getText().toString().trim().length() == 0 || MPvalidateDate.getHint().toString() =="yyyy-mm-dd")
                    MPvalidateDate.setError("Puste pole");
                else
                    {
//TODO KASIA tutaj jak juz ogarniesz te ify to ustaw funkcje updateMyMedicines w odpowiednim miejscu. Bo teraz zostanie ona wywolana wtedy gdy checkBox bedzie zaznaczony
                    if(MPisTaken.isChecked()) {
                        //TODO MACIEJ moim zdaniem to tutaj powinna byc metoda updateMyMedicines
                        updateMyMedicines(MPvalidateDate1, MPquantity1, MPisTaken1);
                    }


                    Intent intent = new Intent(MyPharmacyDetailsActivity.this, MyPharmacyActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

    protected void updateMyMedicines(String Data, String Quantity, String isTaken) {
        myPharmacyDB.setIsTaken(isTaken);
        myPharmacyDB.setExpirationData(Data);
        myPharmacyDB.setHowMany(Quantity);

        final Call<MyPharmacyDB> repo = medicinesService.updateMyPharmacy(token, myPharmacyDB);
        repo.enqueue(new Callback<MyPharmacyDB>() {
            @Override
            public void onResponse(Call<MyPharmacyDB> call, Response<MyPharmacyDB> response) {
                if(response.isSuccessful()) {
                    Log.d("TAG", "Zaktualizowano poprawnie");
                    downloadMyMedicines();
                }
            }

            @Override
            public void onFailure(Call<MyPharmacyDB> call, Throwable t) {
                Log.d("ERROR", t.toString());
                downloadMyMedicines();
            }
        });
    }

    protected void downloadMedicinesById(final int position) {
        Call<MyPharmacyDB> repo = medicinesService.getMyMedicinesById(token, String.valueOf(position));
        repo.enqueue(new Callback<MyPharmacyDB>() {
            @Override
            public void onResponse(Call<MyPharmacyDB> call, Response<MyPharmacyDB> response) {
                if(response.isSuccessful()) {
                    Log.d("TAG", "Udalo sie");
                }
                myPharmacyDB = response.body();
            }

            @Override
            public void onFailure(Call<MyPharmacyDB> call, Throwable t) {
                Log.d("TAG", "Nie udalo sie");

            }
        });
    }

    protected void downloadMyMedicines() {
        medicinesService = setMedicinesService();
        final Call<List<MyPharmacyDB>> repo = medicinesService.getMyPharmacy(token);

        repo.enqueue(new Callback<List<MyPharmacyDB>>() {
            @Override
            public void onResponse(Call<List<MyPharmacyDB>> call, Response<List<MyPharmacyDB>> response) {
                if (response.isSuccessful()) {
                    idList = new ArrayList<>();

                    for (int i = 0; i < response.body().size(); i++) {
                        Log.d("TAG", response.body().get(i).toString());
                        idList.add(response.body().get(i).getId().toString());
                    }
                    myPharmacyDBAdapter.setMyPharmacyDBList(response.body());
                    downloadMedicinesById(Integer.parseInt(position));
                }
            }

            @Override
            public void onFailure(Call<List<MyPharmacyDB>> call, Throwable t) {
                Log.d("ERROR", t.toString());
            }
        });
    }

    public MedicinesService setMedicinesService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.122:8080") // Adres serwera
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        medicinesService = retrofit.create(MedicinesService.class);

        return medicinesService;
    }

}
