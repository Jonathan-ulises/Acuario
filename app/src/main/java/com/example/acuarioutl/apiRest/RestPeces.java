package com.example.acuarioutl.apiRest;

import com.example.acuarioutl.model.Peces;
import com.example.acuarioutl.model.Usuario;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestPeces {
    @GET("api/peces/getAll")
    Call<List<Peces>> getAllPeces();

    @GET("api/user/getAll")
    Call<List<Usuario>> getAllUser();
    
    @POST("api/user/findU")
    @FormUrlEncoded
    Call<ResponseBody> validateUser(@Field("userN") String us, @Field("pass") String ps);

}
