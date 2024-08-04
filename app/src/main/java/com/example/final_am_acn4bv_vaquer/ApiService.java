package com.example.final_am_acn4bv_vaquer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    @GET
    Call<ResponseBody> getImage(@Url String imageUrl);
}