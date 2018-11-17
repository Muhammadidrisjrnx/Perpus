package com.example.rplrus021.myapplication.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface json_api {

    @FormUrlEncoded
    @POST("/insert_pengunjung.php")
    Call<data> daftar(@Field("nama") String nama,
                      @Field("sebagai") String sebagai,
                      @Field("tanggal_kunjung") String tanggal_kunjung);

    @GET("upcoming?api_key=7b91b2135beb96ab098d2f376ee5658b")
    Call<jsonRespond> getJsonUpcoming();
}
