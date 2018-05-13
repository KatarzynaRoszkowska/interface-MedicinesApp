package pl.roszkowska.med;

import android.content.Intent;
import android.nfc.cardemulation.CardEmulation;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CardView scanBtn;
    private TextView contentTxt;
    MenuItem aboutUser;


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
        scanBtn = (CardView) findViewById(R.id.scan);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        aboutUser= (MenuItem)findViewById(R.id.aboutUser);

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

    }

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
            //pobieramy format kodu skanowania
           // String scanFormat = scanningResult.getFormatName();
            //wyświetlamy na ekranie aplikacji
            //formatTxt.setText("FORMAT: "+scanFormat);
            contentTxt.setText("KOD LEKU: "+scanContent);
        }
        else{
            //złe dane zostały pobrane z ZXing
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }




}
