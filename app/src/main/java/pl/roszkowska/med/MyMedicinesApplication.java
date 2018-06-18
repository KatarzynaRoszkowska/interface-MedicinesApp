package pl.roszkowska.med;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private static MyMedicinesApplication myMedicinesApplication;
    MedicinesService medicinesService;
    TokenCredentials token;
    Medicines medicines;
    ResponseAuthentication responseAuthentication;

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


        setMedicinesService();
        authenticateUser();
    }

    public TokenCredentials authenticateUser() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.rememberMe = true;
        loginResponseDto.username = "admin";
        loginResponseDto.password = "admin";

        Call<ResponseAuthentication> userCredentials = medicinesService.authenticate(loginResponseDto);
        try {
            userCredentials.enqueue(new Callback<ResponseAuthentication>() {
                @Override
                public void onResponse(Call<ResponseAuthentication> call, Response<ResponseAuthentication> response) {
                    if (response.isSuccessful()) {
                        Log.d("EA", "Success: " + response.body().toString());

                        Context context = getApplicationContext();
                        CharSequence text = "Login Successful!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        String responseHeaders = response.headers().get("Authorization");
                        token = new TokenCredentials();
                        responseAuthentication = new ResponseAuthentication();
                        token.tokenID = responseHeaders;
                        token.setTokenID(responseHeaders);
                        responseAuthentication.setTokenID(responseHeaders);

                        Log.d("RES_A", token.tokenID);
                        Log.d("RES_A", responseAuthentication.getTokenID());
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
        return token;
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
                .baseUrl("http://192.168.0.31:8080") // Adres serwera
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        medicinesService = retrofit.create(MedicinesService.class);

        return medicinesService;
    }

    public static MyMedicinesApplication getIntent() {
        return myMedicinesApplication;
    }

}
