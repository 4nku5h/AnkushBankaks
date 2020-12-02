package com.example.a4nku5h.ankushbankaks.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static String url="https://api-staging.bankaks.com/task/";
    public  static Retrofit retrofit;

    public  static Retrofit getRetrofitClient(){
        if(retrofit==null){
              retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }




}
