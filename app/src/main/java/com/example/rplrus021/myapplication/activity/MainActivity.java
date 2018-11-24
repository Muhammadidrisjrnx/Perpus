package com.example.rplrus021.myapplication.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rplrus021.myapplication.R;
import com.example.rplrus021.myapplication.model.model_pengunjung;
import com.example.rplrus021.myapplication.network.config_url;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private EditText edit_text_name, edit_text_as, edit_text_date;
    private Button button_add;
    private String visit_date;
    private model_pengunjung model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_text_name = (EditText) findViewById(R.id.edit_text_name);
        edit_text_as = (EditText) findViewById(R.id.edit_text_as);
        button_add = (Button) findViewById(R.id.button_add);
        edit_text_date = (EditText) findViewById(R.id.edit_text_date);
        model = new model_pengunjung();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        visit_date = simpleDateFormat1.format(calendar.getTime());
        edit_text_date.setText(visit_date);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setNama(edit_text_name.getText().toString());
                model.setSebagai(edit_text_as.getText().toString());
                model.setTanggal(visit_date);
                if (model.getNama().trim().isEmpty() || model.getSebagai().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Write name and as", Toast.LENGTH_SHORT).show();
                } else {
                    new insert_pengunjung().execute();
                }

            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class insert_pengunjung extends AsyncTask<Void, Void, JSONObject> {
    ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            //kasih loading
            progressDialog = ProgressDialog.show(MainActivity.this,"Loading","Please Wait",true);
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject;

            try {
                String tmpName = model.getNama().replaceAll(" ", "%20");
                String tmpSebagai = model.getSebagai().replaceAll(" ", "%20");
                String tmpTanggal_kunjung= model.getSebagai().replaceAll(" ", "%20");
                String url = config_url.url + "perpus/aksi_daftar.php?nama=" + tmpName + "&&sebagai=" + tmpSebagai + "&&tanggal_kunjung=" + tmpTanggal_kunjung + "";
                System.out.println(url);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"
                ), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                inputStream.close();
                String json = stringBuilder.toString();
                jsonObject = new JSONObject(json);
            } catch (Exception e) {
                jsonObject = null;
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            Log.d("hasil json ", "onPostExecute: " + jsonObject.toString());

            if (jsonObject != null) {
                try {
                    JSONObject Result = jsonObject.getJSONObject("Result");
                    String sukses = Result.getString("Sukses");
                    Log.d("hasil sukses ", "onPostExecute: " + sukses);
                    if (sukses.equals("true")) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        //to main menu
                    } else if (sukses.equals("false")) {
                        Toast.makeText(getApplicationContext(), "Fails", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            } else {
            }
        }
    }

}

