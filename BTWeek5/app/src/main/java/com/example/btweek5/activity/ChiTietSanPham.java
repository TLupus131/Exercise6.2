package com.example.btweek5.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btweek5.Interface.IPConfig;
import com.example.btweek5.R;
import com.example.btweek5.Interface.InterfaceSanPham;
import com.example.btweek5.Response.SanPhamResponse;
import com.example.btweek5.entities.DanhMuc;
import com.example.btweek5.entities.SpinnerItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChiTietSanPham extends AppCompatActivity {

    private Button btnCreate, btnUpdate, btnDelete;
    private TextView tvMa;
    private EditText edtTen, edtDonGia;
    private ImageButton btnReturn;
    private Spinner spinner;
    private String placeholder;
    private List<SpinnerItem> spinnerItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        btnCreate = findViewById(R.id.btnCreateSanPham);
        btnUpdate = findViewById(R.id.btnUpdateSanPham);
        btnDelete = findViewById(R.id.btnDeleteSanPham);
        tvMa = findViewById(R.id.textViewMa);
        edtTen = findViewById(R.id.edtTen);
        edtDonGia = findViewById(R.id.edtDonGia);
        spinner = findViewById(R.id.spinnerDanhMuc);
        btnReturn = findViewById(R.id.btnReturn);

        int ma = getIntent().getIntExtra("Ma", 0);
        String ten = getIntent().getStringExtra("Ten");
        double donGia = getIntent().getDoubleExtra("DonGia", 0.0);
        String tenDanhMuc = getIntent().getStringExtra("TenDanhMuc");
        placeholder = tenDanhMuc;
        new FetchDanhMuc().execute();
        tvMa.setText(String.valueOf(ma));
        edtTen.setHint(ten);
        edtDonGia.setHint(String.valueOf(donGia));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return();
            }
        });

        btnCreate.setOnClickListener(v -> {
            int maSanPham = Integer.parseInt(tvMa.getText().toString());
            String tenSanPham;
            double donGiaSanPham = 0;
            if (edtTen.getText().toString().isEmpty()) {
                tenSanPham = edtTen.getHint().toString();
            } else {
                tenSanPham = edtTen.getText().toString();
            }
            if (edtDonGia.getText().toString().isEmpty()) {
                donGiaSanPham = Double.parseDouble(edtDonGia.getHint().toString());
            } else {
                donGiaSanPham = Double.parseDouble(edtDonGia.getText().toString());
            }
            SpinnerItem selectedItem = (SpinnerItem) spinner.getSelectedItem();
            DanhMuc danhMuc = new DanhMuc(selectedItem.getValue(), selectedItem.getDisplayText());
            SanPhamResponse sanPhamResponse = new SanPhamResponse(maSanPham, tenSanPham, donGiaSanPham, danhMuc.getMaDanhMuc());
            insert(sanPhamResponse);
        });

        btnUpdate.setOnClickListener(v -> {
            int maSanPham = Integer.parseInt(tvMa.getText().toString());
            String tenSanPham;
            double donGiaSanPham = 0;
            if (edtTen.getText().toString().isEmpty()) {
                tenSanPham = edtTen.getHint().toString();
            } else {
                tenSanPham = edtTen.getText().toString();
            }
            if (edtDonGia.getText().toString().isEmpty()) {
                donGiaSanPham = Double.parseDouble(edtDonGia.getHint().toString());
            } else {
                donGiaSanPham = Double.parseDouble(edtDonGia.getText().toString());
            }
            SpinnerItem selectedItem = (SpinnerItem) spinner.getSelectedItem();
            DanhMuc danhMuc = new DanhMuc(selectedItem.getValue(), selectedItem.getDisplayText());
            SanPhamResponse sanPhamResponse = new SanPhamResponse(maSanPham, tenSanPham, donGiaSanPham, danhMuc.getMaDanhMuc());
            update(sanPhamResponse);
        });

        btnDelete.setOnClickListener(v -> {
            int maSanPham = Integer.parseInt(tvMa.getText().toString());
            delete(maSanPham);
        });
    }

    private void insert(SanPhamResponse sanPhamResponse){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + IPConfig.IPv4 + ":8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        InterfaceSanPham interfaceSanPham = retrofit.create(InterfaceSanPham.class);
        Call<SanPhamResponse> call = interfaceSanPham.insert(sanPhamResponse);
        call.enqueue(new Callback<SanPhamResponse>() {
            @Override
            public void onResponse(Call<SanPhamResponse> call, Response<SanPhamResponse> response) {
                Toast.makeText(ChiTietSanPham.this, "Insert product successful!", Toast.LENGTH_LONG).show();
                Return();
            }

            @Override
            public void onFailure(Call<SanPhamResponse> call, Throwable t) {
                Toast.makeText(ChiTietSanPham.this, "Failed to insert product: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Return(){
        Intent intent = new Intent(ChiTietSanPham.this, SanPhamActivity.class);
        startActivity(intent);
    }

    private void update(SanPhamResponse sanPhamResponse){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + IPConfig.IPv4 + ":8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        InterfaceSanPham interfaceSanPham = retrofit.create(InterfaceSanPham.class);
        Call<SanPhamResponse> call = interfaceSanPham.update(sanPhamResponse);
        call.enqueue(new Callback<SanPhamResponse>() {
            @Override
            public void onResponse(Call<SanPhamResponse> call, Response<SanPhamResponse> response) {
                Toast.makeText(ChiTietSanPham.this, "Updated product successful!", Toast.LENGTH_LONG).show();
                Return();
            }

            @Override
            public void onFailure(Call<SanPhamResponse> call, Throwable t) {
                Toast.makeText(ChiTietSanPham.this, "Failed to update product: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void delete(int id){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + IPConfig.IPv4 + ":8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        InterfaceSanPham interfaceSanPham = retrofit.create(InterfaceSanPham.class);
        Call<Void> call = interfaceSanPham.delete(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ChiTietSanPham.this, "Deleted product successful!", Toast.LENGTH_LONG).show();
                Return();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ChiTietSanPham.this, "Failed to delete product: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private class FetchDanhMuc extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL("http://" + IPConfig.IPv4 + ":8080/api/danh_muc");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return response.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.isEmpty()) {
                try {
                    JSONArray propertiesArray = new JSONArray(s);

                    for (int i = 0; i < propertiesArray.length(); i++) {
                        JSONObject pObject = propertiesArray.getJSONObject(i);
                        int ma = pObject.getInt("maDanhMuc");
                        String ten = pObject.getString("tenDanhMuc");
                        spinnerItems.add(new SpinnerItem(ten, ma));
                    }

                    ArrayAdapter<SpinnerItem> spinnerAdapter = new ArrayAdapter<>(ChiTietSanPham.this, android.R.layout.simple_spinner_item, spinnerItems);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Spinner spinner = findViewById(R.id.spinnerDanhMuc);
                    spinner.setAdapter(spinnerAdapter);

                    for (int i = 0; i < spinnerItems.size(); i++) {
                        SpinnerItem item = spinnerItems.get(i);
                        if (item.getDisplayText().equals(placeholder)) {
                            spinner.setSelection(i);
                            break;
                        }
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}