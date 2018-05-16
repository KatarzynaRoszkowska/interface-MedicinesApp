package pl.roszkowska.med;

import java.util.List;

import pl.roszkowska.med.LoginResponseDto;
import pl.roszkowska.med.ResponseAuthentication;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @POST("api/authenticate")
    Call<ResponseAuthentication> authenticate(@Body LoginResponseDto loginResponseDto);

    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET("/api/producers")
    Call<List<Producers>> getProducers(@Header("Authorization") String authorization);
}
