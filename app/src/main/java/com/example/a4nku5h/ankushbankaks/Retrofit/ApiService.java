package com.example.a4nku5h.ankushbankaks.Retrofit;

import com.example.a4nku5h.ankushbankaks.model.RootModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService{
    @GET("{typeId}")
    Call<RootModel> getResultById(@Path("typeId") String id);
}