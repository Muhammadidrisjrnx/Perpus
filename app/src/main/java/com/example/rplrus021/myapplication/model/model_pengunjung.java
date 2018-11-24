package com.example.rplrus021.myapplication.model;

public class model_pengunjung {
    private String id;
    private String nama;
    private String sebagai;

    public model_pengunjung(String id, String nama, String sebagai, String tanggal) {
        this.id = id;
        this.nama = nama;
        this.sebagai = sebagai;
        this.tanggal = tanggal;
    }

    public model_pengunjung(){

    }

    private String tanggal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSebagai() {
        return sebagai;
    }

    public void setSebagai(String sebagai) {
        this.sebagai = sebagai;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

}
