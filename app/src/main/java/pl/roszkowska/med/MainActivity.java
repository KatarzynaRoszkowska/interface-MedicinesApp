package pl.roszkowska.med;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import pl.roszkowska.med.api.appInformation.AppInformationActivity;
import pl.roszkowska.med.api.Scanner.ScanTheCode;
import pl.roszkowska.med.api.medicines.MedicinesActivity;
import pl.roszkowska.med.api.myPharmacy.MyPharmacyActivity;
import pl.roszkowska.med.api.producers.Producers;
import pl.roszkowska.med.api.service.MedicinesService;
import pl.roszkowska.med.api.userInformation.UserInformationActivity;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LoginResponseDto loginResponseDto;
    private Retrofit retrofit;
    private TokenCredentials token;
    private MedicinesService service;
    private Producers producers;
    private CardView scanBtn;
    MenuItem aboutUser;
    private CardView myPharm;
    private CardView medicines;
    TextView email, name;


    public MedicinesService getService() {
        return service;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //instantiate UI items
        scanBtn = findViewById(R.id.scan);
        aboutUser = findViewById(R.id.aboutUser);
        myPharm = findViewById(R.id.myPharmacy1);
        medicines = findViewById(R.id.listOfMedicines);



       /* SharedPreferences sp = getSharedPreferences("dane", Context.MODE_PRIVATE);
        //View navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_main);
         email = navigationView.findViewById(R.id.emailUser);
         name = navigationView.findViewById(R.id.nameUser);
         name.setText(String.format("%s", sp.getString(SetUserInfoActivity.NAME_PREFS, "Nieznany")));
         email.setText(String.format("%s", sp.getString(SetUserInfoActivity.USER_EMAIL, "Nieznany")));*/




        //listen for clicks
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate ZXing integration class
                IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
                //start scanning
                scanIntegrator.initiateScan();

            }
        });

        myPharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyPharmacyActivity.class);
                startActivity(intent);
            }
        });

        medicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicinesActivity.class);
                startActivity(intent);
            }
        });

//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.0.122:8080") // Adres serwera
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        service = retrofit.create(MedicinesService.class);
//
//        authenticateUser();
    }

//    private void authenticateUser() {
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        LoginResponseDto loginResponseDto = new LoginResponseDto();
//        loginResponseDto.rememberMe = true;
//        loginResponseDto.username = "admin";
//        loginResponseDto.password = "admin";
//
//        Call<ResponseAuthentication> userCredentials = service.authenticate(loginResponseDto);
//        try {
////            userCredentials.execute();
//            userCredentials.enqueue(new Callback<ResponseAuthentication>() {
//                @Override
//                public void onResponse(Call<ResponseAuthentication> call, Response<ResponseAuthentication> response) {
//                    if(response.isSuccessful()) {
//                        Log.d("EA", "Success: " + response.body().toString());
////                    }
//
//                        Context context = getApplicationContext();
//                        CharSequence text = "Login Successful!";
//                        int duration = Toast.LENGTH_SHORT;
//
//                        Toast toast = Toast.makeText(context, text, duration);
//                        toast.show();
//
//                        String responseHeaders = response.headers().get("Authorization");
//                        token = new TokenCredentials();
//                        token.tokenID = responseHeaders;
//
//                        Log.d("EA", token.tokenID);
//
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseAuthentication> call, Throwable t) {
//                    Context context = getApplicationContext();
//                    CharSequence text = "FAIL :(";
//                    int duration = Toast.LENGTH_SHORT;
//
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();
//                }
//            });
//        } catch (Exception exception) {
//            Log.e("EA", "Exception: " + exception.toString());
//        }
//    }

//    private void getProducers() {
//        final Call<List<Producers>> producers = service.getProducers(token.tokenID);
//
//        try {
//    producers.enqueue(new Callback<List<Producers>>() {
//        @Override
//        public void onResponse(Call<List<Producers>> call, Response<List<Producers>> response) {
//            if(response.isSuccessful()) {
//                Log.d("EA", "Success: " + response.body().toString());
//
//                Producers producers1 = response.body().get(0);
//                Log.d("EA", producers1.producerName);
//                Log.d("EA", producers1.country);
//                Log.d("EA", producers1.town);
//                Log.d("EA", producers1.address);
//
//                Context context = getApplicationContext();
//                CharSequence text = "Udalo sie";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//            }
//        }
//
//        @Override
//        public void onFailure(Call<List<Producers>> call, Throwable t) {
//            Context context = getApplicationContext();
//            CharSequence text = "NIE Udalo sie";
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//        }
//    });
//        } catch (Exception exception) {
//            Log.e("EA","Failed: " + token.toString());
//        }
//    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.) {

        //  return false;
        // }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.aboutUser) {
            Intent userIntent = new Intent(MainActivity.this, UserInformationActivity.class);
            MainActivity.this.startActivity(userIntent);
        } else if (id == R.id.aboutApp) {
            Intent appIntent = new Intent(MainActivity.this, AppInformationActivity.class);
            MainActivity.this.startActivity(appIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //pobranie wyniku za pomocą klasy IntentResult
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //sprawdzenie czy mamy poprawny wynik
        if (scanningResult != null) {
            //pobieramy wynik skanowania
            String scanContent = scanningResult.getContents();
            //nowa aktywnosc: ScanTheCode + przekazanie parametru kody EAN
            Intent i = new Intent(MainActivity.this, ScanTheCode.class);
            i.putExtra("ean",scanContent);
            startActivity(i);
        } else {
            //złe dane zostały pobrane z ZXing
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}
