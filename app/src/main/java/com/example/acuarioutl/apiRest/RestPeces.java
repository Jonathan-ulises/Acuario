package com.example.acuarioutl.apiRest;

import com.example.acuarioutl.model.Peces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestPeces {
    @GET("api/peces/getAll")
    Call<List<Peces>> getAllPeces();
}
