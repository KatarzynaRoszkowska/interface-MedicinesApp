package pl.roszkowska.med.api.appInformation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pl.roszkowska.med.R;

/**
 * The class responsible for displaying information about the application
 */
public class AppInformationActivity extends AppCompatActivity {

    /**
     * the method that displays activity_app_information layout
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_information);
    }

    /**
     * the method that goes to the github.com website and the display profile of the application developer - Katarzyna Roszkowska
     */

    public void KateOnClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KatarzynaRoszkowska"));
        startActivity(browserIntent);

    }

    /**
     * the method that goes to the github.com website and the display profile of the application developer - Maciej Ziółkowski
     */

    public void MaciekOnClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mziolkowski"));
        startActivity(browserIntent);
    }
}
