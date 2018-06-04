package pl.roszkowska.med.api.service;

import com.google.gson.annotations.Expose;

import java.util.List;

import pl.roszkowska.med.LoginResponseDto;
import pl.roszkowska.med.api.medicines.Medicines;
import pl.roszkowska.med.api.myPharmacy.MyPharmacy;
import pl.roszkowska.med.api.myPharmacy.MyPharmacyDB;
//import pl.roszkowska.med.api.producers.Producers;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MedicinesService {
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @POST("api/authenticate")
    Call<ResponseAuthentication> authenticate(@Body LoginResponseDto loginResponseDto);

    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET("/api/my-pharmacies")
    Call<List<MyPharmacyDB>> getMyPharmacy(@Header("Authorization") String authorization);

    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET("/api/medicines/{id}")
    Call<Medicines> getMedicinesById(@Header("Authorization") String authorization, @Path("id") String id);

    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET("/api/medicines")
    Call<List<Medicines>> getMedicines(@Header("Authorization") String authorization);

    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET("/api/medicines/ean/{ean}")
    Call<Medicines> getMedicinesEan(@Header("Authorization") String authorization, @Path("ean") String ean);

    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @POST("/api/my-pharmacies")
//    Call<MyPharmacyDB> addMedicines(@Header("Authorization") String authorization, @Body Medicines medicines);

    Call<MyPharmacyDB> addMedicines(@Header("Authorization") String authorization,
                                    @Body Medicines medicines);

//    Call<MyPharmacyDB> addMedicines(@Header("Authorization") String authorization, @Field("id") String id);
}
