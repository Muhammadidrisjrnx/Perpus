package com.example.rplrus021.myapplication.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface json_api {

    @POST("insert.php")
    @FormUrlEncoded
    Call<data> daftar(
            @Field("userId")String userId,
            @Field("name") String name,
            @Field("sebagai") String sebagai,
            @Field("tanggal_kunjung") String tanggal_kunjung);

}
