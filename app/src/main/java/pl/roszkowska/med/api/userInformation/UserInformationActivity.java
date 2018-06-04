package pl.roszkowska.med.api.userInformation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.roszkowska.med.R;

public class UserInformationActivity extends AppCompatActivity {

    Button userInfo;
    TextView userName, userMedicinesINFO, userContactINFO, userAllergieINFO, userEMailINFO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information_activity);

        userInfo = findViewById(R.id.UserInfobutton);
        userName = findViewById(R.id.userName);
        userMedicinesINFO = findViewById(R.id.userMedicinesINFO);
        userContactINFO = findViewById(R.id.userContactINFO);
        userAllergieINFO = findViewById(R.id.userAllergieINFO);
        userEMailINFO = findViewById(R.id.userEMailINFO);

        SharedPreferences sp = getSharedPreferences("dane", Context.MODE_PRIVATE);

        userName.setText(String.format("%s", sp.getString(SetUserInfoActivity.NAME_PREFS, "Nieznany")));
        userMedicinesINFO.setText(String.format("%s", sp.getString(SetUserInfoActivity.USER_MEDICINES, "Nieznany")));
      //  userContactINFO.setText(String.format("%s", sp.getString(SetUserInfoActivity.U, "Nieznany")));
        userAllergieINFO.setText(String.format("%s", sp.getString(SetUserInfoActivity.USER_ALERGIE, "Nieznany")));
        userEMailINFO.setText(String.format("%s", sp.getString(SetUserInfoActivity.USER_EMAIL, "Nieznany")));


       userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInformationActivity.this,SetUserInfoActivity.class);
                startActivity(intent);

            }
        });
    }
}
