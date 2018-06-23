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
import pl.roszkowska.med.api.intent.IntentIntegrator;
import pl.roszkowska.med.api.intent.IntentResult;
import pl.roszkowska.med.api.medicines.MedicinesActivity;
import pl.roszkowska.med.api.myPharmacy.MyPharmacyActivity;
import pl.roszkowska.med.api.producers.Producers;
import pl.roszkowska.med.api.service.MedicinesService;
import pl.roszkowska.med.api.userInformation.UserInformationActivity;
import retrofit2.Retrofit;

/**
 * This class is the main activity and display main content.
 */
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

    // the method that displays activity_main layout
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

        //listen for clicks scanBtn. After clicked the button, barcode scanning activity opens
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate ZXing integration class
                IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
                //start scanning
                scanIntegrator.initiateScan();

            }
        });

        //listen for clicks myPharm. After clicked the button, myPharmacy activity opens
        myPharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyPharmacyActivity.class);
                startActivity(intent);
            }
        });

        //listen for clicks medicines. After clicked the button, medicines activity opens
        medicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicinesActivity.class);
                startActivity(intent);
            }
        });

    }

//


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
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // After item clicked aboutUser activity opens
        if (id == R.id.aboutUser) {
            Intent userIntent = new Intent(MainActivity.this, UserInformationActivity.class);
            MainActivity.this.startActivity(userIntent);

            // After item clicked aboutApp activity opens
        } else if (id == R.id.aboutApp) {
            Intent appIntent = new Intent(MainActivity.this, AppInformationActivity.class);
            MainActivity.this.startActivity(appIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This metod validates the results
     * @param requestCode downloaded code
     * @param resultCode processed
     * @param intent These are the possible flags that can be used
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //pobranie wyniku za pomocą klasy IntentResult
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //sprawdzenie czy mamy poprawny wynik
        if (scanningResult != null) {
            //pobieramy wynik skanowania
            String scanContent = scanningResult.getContents();
            //nowa aktywnosc: ScanTheCode + przekazanie parametru kody EAN
            Intent i = new Intent(MainActivity.this, ScanTheCode.class);
            i.putExtra("ean", scanContent);
            startActivity(i);
        } else {
            //złe dane zostały pobrane z ZXing
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}
