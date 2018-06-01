package pl.roszkowska.med;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pl.roszkowska.med.api.medicines.Medicines;
import pl.roszkowska.med.api.myPharmacy.MyPharmacyDB;
import pl.roszkowska.med.api.service.MedicinesService;
import pl.roszkowska.med.api.service.ResponseAuthentication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyMedicinesApplication extends Application {
    MedicinesService medicinesService;
    TokenCredentials token;
    Medicines medicines;
    ResponseAuthentication responseAuthentication;
    private MyMedicinesApplication myMedicinesApplication;

    public ResponseAuthentication getResponseAuthentication() {
        return responseAuthentication;
    }

    public MedicinesService getMedicinesService() {
        return medicinesService;
    }

    public TokenCredentials getToken() {
        return token;
    }

    public Medicines getMedicines() {
        return medicines;
    }

    public MyMedicinesApplication() {
        onCreate();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.3:8080") // Adres serwera
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        medicinesService = retrofit.create(MedicinesService.class);

                authenticateUser();
    }

    private void authenticateUser() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.rememberMe = true;
        loginResponseDto.username = "admin";
        loginResponseDto.password = "admin";

        Call<ResponseAuthentication> userCredentials = medicinesService.authenticate(loginResponseDto);
        try {
//            userCredentials.execute();
            userCredentials.enqueue(new Callback<ResponseAuthentication>() {
                @Override
                public void onResponse(Call<ResponseAuthentication> call, Response<ResponseAuthentication> response) {
                    if(response.isSuccessful()) {
                        Log.d("EA", "Success: " + response.body().toString());
//                    }

                        Context context = getApplicationContext();
                        CharSequence text = "Login Successful!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        String responseHeaders = response.headers().get("Authorization");
                        token = new TokenCredentials();
                        responseAuthentication= new ResponseAuthentication();
                        token.tokenID = responseHeaders;
                        token.setTokenID(responseHeaders);
                        responseAuthentication.setTokenID(responseHeaders);

                        Log.d("RES_A", token.tokenID);
                        Log.d("RES_A", responseAuthentication.getTokenID());
//                        downloadMyMedicines();

                    }
                }

                @Override
                public void onFailure(Call<ResponseAuthentication> call, Throwable t) {
                    Context context = getApplicationContext();
                    CharSequence text = "FAIL :(";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });
        } catch (Exception exception) {
            Log.e("EA", "Exception: " + exception.toString());
        }
    }

    private void downloadMyMedicines() {
        Call<List<MyPharmacyDB>> repo = medicinesService.getMyPharmacy(responseAuthentication.getTokenID());

        repo.enqueue(new Callback<List<MyPharmacyDB>>() {
            @Override
            public void onResponse(Call<List<MyPharmacyDB>> call, Response<List<MyPharmacyDB>> response) {
                if(response.isSuccessful()) {
//                    myPharmacyDBAdapter.setMyPharmacyDBList(response.body());
                    for(int i=0; i<response.body().size();i++) {
//                        MyPharmacyDB myPharmacyDB = response.body().get(i);
                        Log.d("TAG", response.body().get(i).toString());
//                        Log.d("TAG", myPharmacyDB.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MyPharmacyDB>> call, Throwable t) {
                Log.d("ERROR", t.toString());
            }
        });
    }

}
