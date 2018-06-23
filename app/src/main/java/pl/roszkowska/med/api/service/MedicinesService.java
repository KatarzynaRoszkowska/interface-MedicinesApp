package pl.roszkowska.med.api.service;

import java.util.List;

import pl.roszkowska.med.LoginResponseDto;
import pl.roszkowska.med.api.medicines.Medicines;
import pl.roszkowska.med.api.myPharmacy.MyPharmacyDB;
//import pl.roszkowska.med.api.producers.Producers;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * This interface turns your HTTP API into a Java interface.
 */

public interface MedicinesService {

    /**
     * POST create authentication
     * @param loginResponseDto login data
     * @return Token
     */
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @POST("api/authenticate")
    Call<ResponseAuthentication> authenticate(@Body LoginResponseDto loginResponseDto);

    /**
     * GET get list of medicines from MyPharmacy
     * @param authorization token from authorization
     * @return List of medicines
     */
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET("/api/my-pharmacies")
    Call<List<MyPharmacyDB>> getMyPharmacy(@Header("Authorization") String authorization);

    /**
     * GET get medicine by ID from Medicines
     * @param authorization token from authorization
     * @param id ID medicines from DB
     * @return List of medicines
     */
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET("/api/medicines/{id}")
    Call<Medicines> getMedicinesById(@Header("Authorization") String authorization, @Path("id") String id);

    /**
     * GET get list of all medicines from Medicines
     * @param authorization token from authorization
     * @return List of medicines
     */
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET("/api/medicines")
    Call<List<Medicines>> getMedicines(@Header("Authorization") String authorization);

    /**
     * GET get medicine by EAN code from Medicines
     * @param authorization token from authorization
     * @param ean EAN code
     * @return medicine
     */
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET("/api/medicines/ean/{ean}")
    Call<Medicines> getMedicinesEan(@Header("Authorization") String authorization, @Path("ean") String ean);

    /**
     * POST create medicine in MyPharmacy
     * @param authorization token from authorization
     * @param myPharmacyDB the body MyPharmacy
     * @return new medicine
     */
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @POST("/api/my-pharmacies")
    Call<MyPharmacyDB> addMedicines(@Header("Authorization") String authorization, @Body MyPharmacyDB myPharmacyDB);

    /**
     * DELETE delete medicine by ID
     * @param authorization token from authorization
     * @param id ID medicine from MyPharmacy
     * @return delete medicine
     */
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @DELETE("/api/my-pharmacies/{id}")
    Call<MyPharmacyDB> deleteMyPharmacie(@Header("Authorization") String authorization, @Path("id") String id);

    /**
     * PUT update medicine
     * @param authorization token from authorization
     * @param myPharmacyDB body MyPharmacy
     * @return updated medicine
     */
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @PUT("/api/my-pharmacies")
    Call<MyPharmacyDB> updateMyPharmacy(@Header("Authorization") String authorization, @Body MyPharmacyDB myPharmacyDB);

    /**
     * GET get medicine by ID from MyPharmacy
     * @param authorization token from authorization
     * @param id of medicine which we can get
     * @return medicine
     */
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET("/api/my-pharmacies/{id}")
    Call<MyPharmacyDB> getMyMedicinesById(@Header("Authorization") String authorization, @Path("id") String id);
}

