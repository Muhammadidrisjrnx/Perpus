package com.example.rplrus021.myapplication.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("as")
    @Expose
    private String as;
    @SerializedName("tanggal_kunjung")
    @Expose
    private String tanggal_kunjung;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTanggal_kunjung() {
        return tanggal_kunjung;
    }

    public String getName() {
        return name;
    }

    public String getAs() {
        return as;
    }
}
