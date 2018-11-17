package com.example.rplrus021.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.rplrus021.myapplication.network.data;
import com.example.rplrus021.myapplication.network.jsonRespond;
import com.example.rplrus021.myapplication.network.json_api;
import com.example.rplrus021.myapplication.network.retrofitclientinstance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText edit_text_name, edit_text_as, edit_text_date;
    private Button button_add;
    private String name, as, visit_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_text_name = (EditText) findViewById(R.id.edit_text_name);
        edit_text_as = (EditText) findViewById(R.id.edit_text_as);
        button_add = (Button) findViewById(R.id.button_add);

        calendar_setting();

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edit_text_name.getText().toString();
                as = edit_text_as.getText().toString();
                if (name.trim().isEmpty() || as.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Write name and as", Toast.LENGTH_SHORT).show();
                } else {
                    savedata();
                }

            }
        });
    }

    private void calendar_setting() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        visit_date = simpleDateFormat1.format(calendar.getTime());
    }

    private void savedata() {
        json_api service = retrofitclientinstance.getRetrofitInstance().create(json_api.class);
        Call<data> call = service.daftar(name, as, visit_date);
        call.enqueue(new Callback<data>() {
            @Override
            public void onResponse(Call<data> call, Response<data> response) {
                String nama = response.body().getName();
                String sebagai = response.body().getAs();
                String tanggal_kunjung = response.body().getTanggal_kunjung();
                Toast.makeText(getApplicationContext(), nama, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), sebagai, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), tanggal_kunjung, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<data> call, Throwable t) {
                Log.d("responku", "onFailure: gagal");
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }
}

