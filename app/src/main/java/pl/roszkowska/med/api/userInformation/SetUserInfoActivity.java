package pl.roszkowska.med.api.userInformation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pl.roszkowska.med.R;


// A class that allows you to enter information about the user of the application, e.g. for what is allergic,
// who to contact in the event of an accident
public class SetUserInfoActivity extends AppCompatActivity {

    Button userInfo;
    EditText input_name,input_email, input_medicines, input_number, input_alergie;
    public static final String NAME_PREFS = "USER_NAME";
    public static final String USER_EMAIL = "USER_EMAIL";
    public static final String USER_MEDICINES = "USER_MEDICINES";
    public static final String USER_NUMBER = "USER_NUMBER";
    public static final String USER_ALERGIE = "USER_ALERGIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_info);

        userInfo = findViewById(R.id.btn_login);
        input_name = findViewById(R.id.input_name);
        input_email = findViewById(R.id.input_email);
        input_medicines = findViewById(R.id.input_medicines);
        input_number = findViewById(R.id.input_number);
        input_alergie = findViewById(R.id.input_alergie);

        SharedPreferences sharedPref = getSharedPreferences("dane", Context.MODE_PRIVATE);



        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_name.getText().toString().isEmpty()) {
                    input_name.setError(null);
                    SharedPreferences sharedPref = getSharedPreferences("dane", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPref.edit();

                    edit.putString(NAME_PREFS, input_name.getText().toString());
                    edit.putString(USER_EMAIL, input_email.getText().toString());
                    edit.putString(USER_MEDICINES, input_medicines.getText().toString());
                    edit.putString(USER_NUMBER, input_number.getText().toString());
                    edit.putString(USER_ALERGIE, input_alergie.getText().toString());

                    edit.apply();
                    finish();
                    Intent intent = new Intent(SetUserInfoActivity.this, UserInformationActivity.class);
                    startActivity(intent);
                } else {
                    input_name.setError("Podaj imię!");
                }

                Intent intent = new Intent(SetUserInfoActivity.this,UserInformationActivity.class);
                startActivity(intent);


            }
        });
    }
}
