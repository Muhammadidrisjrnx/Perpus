package com.example.rplrus021.myapplication.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sebagai")
    @Expose
    private String sebagai;
    @SerializedName("tanggal_kunjung")
    @Expose
    private String tanggalKunjung;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSebagai() {
        return sebagai;
    }

    public void setSebagai(String sebagai) {
        this.sebagai = sebagai;
    }

    public String getTanggalKunjung() {
        return tanggalKunjung;
    }

    public void setTanggalKunjung(String tanggalKunjung) {
        this.tanggalKunjung = tanggalKunjung;
    }
}
