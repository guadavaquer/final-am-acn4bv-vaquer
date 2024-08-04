package com.example.final_am_acn4bv_vaquer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://replit.com/"; // Base URL
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // Establece la URL base
                    .addConverterFactory(GsonConverterFactory.create()) // Aunque no uses Gson para ResponseBody, es una buena práctica tenerlo
                    .build();
        }
        return retrofit;
    }
}