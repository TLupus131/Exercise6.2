package com.example.btweek5.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btweek5.Interface.IPConfig;
import com.example.btweek5.Interface.InterfaceDanhMuc;
import com.example.btweek5.Interface.InterfaceSanPham;
import com.example.btweek5.R;
import com.example.btweek5.Response.DanhMucResponse;
import com.example.btweek5.Response.SanPhamResponse;
import com.example.btweek5.entities.DanhMuc;
import com.example.btweek5.entities.SpinnerItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChiTietDanhMuc extends AppCompatActivity {

    private Button btnCreate, btnUpdate, btnDelete;
    private ImageButton btnReturn;

    private TextView tvMaDanhMuc;
    private EditText edtTenDanhMuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_danh_muc);
        btnCreate = findViewById(R.id.btnCreateDanhMuc);
        btnUpdate = findViewById(R.id.btnUpdateDanhMuc);
        btnDelete = findViewById(R.id.btnDeleteDanhMuc);
        tvMaDanhMuc = findViewById(R.id.textViewMaDanhMuc);
        edtTenDanhMuc = findViewById(R.id.edtTenDanhMuc);
        btnReturn = findViewById(R.id.btnReturn2);

        int ma = getIntent().getIntExtra("MaDanhMuc", 0); // Default value is 0
        String ten = getIntent().getStringExtra("TenDanhMuc");

        tvMaDanhMuc.setText(String.valueOf(ma));
        edtTenDanhMuc.setHint(ten);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return();
            }
        });

        btnCreate.setOnClickListener(v -> {
            int maDanhMuc = Integer.parseInt(tvMaDanhMuc.getText().toString());
            String tenDanhMuc;
            if (edtTenDanhMuc.getText().toString().isEmpty()) {
                tenDanhMuc = edtTenDanhMuc.getHint().toString();
            } else {
                tenDanhMuc = edtTenDanhMuc.getText().toString();
            }
            DanhMucResponse danhMucResponse = new DanhMucResponse(maDanhMuc, tenDanhMuc);
            insert(danhMucResponse);
        });

        btnUpdate.setOnClickListener(v -> {
            int maDanhMuc = Integer.parseInt(tvMaDanhMuc.getText().toString());
            String tenDanhMuc;
            if (edtTenDanhMuc.getText().toString().isEmpty()) {
                tenDanhMuc = edtTenDanhMuc.getHint().toString();
            } else {
                tenDanhMuc = edtTenDanhMuc.getText().toString();
            }
            DanhMucResponse danhMucResponse = new DanhMucResponse(maDanhMuc, tenDanhMuc);
            update(danhMucResponse);
        });

        btnDelete.setOnClickListener(v -> {
            int maDanhMuc = Integer.parseInt(tvMaDanhMuc.getText().toString());
            delete(maDanhMuc);
        });
    }

    private void insert(DanhMucResponse danhMucResponse){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + IPConfig.IPv4 + ":8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        InterfaceDanhMuc interfaceDanhMuc = retrofit.create(InterfaceDanhMuc.class);
        Call<DanhMucResponse> call = interfaceDanhMuc.insert(danhMucResponse);
        call.enqueue(new Callback<DanhMucResponse>() {
            @Override
            public void onResponse(Call<DanhMucResponse> call, Response<DanhMucResponse> response) {
                Toast.makeText(ChiTietDanhMuc.this, "Insert category successful!", Toast.LENGTH_LONG).show();
                Return();
            }

            @Override
            public void onFailure(Call<DanhMucResponse> call, Throwable t) {
                Toast.makeText(ChiTietDanhMuc.this, "Failed to insert category: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void update(DanhMucResponse danhMucResponse){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + IPConfig.IPv4 + ":8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        InterfaceDanhMuc interfaceDanhMuc = retrofit.create(InterfaceDanhMuc.class);
        Call<DanhMucResponse> call = interfaceDanhMuc.update(danhMucResponse);
        call.enqueue(new Callback<DanhMucResponse>() {
            @Override
            public void onResponse(Call<DanhMucResponse> call, Response<DanhMucResponse> response) {
                Toast.makeText(ChiTietDanhMuc.this, "Updated category successful!", Toast.LENGTH_LONG).show();
                Return();
            }

            @Override
            public void onFailure(Call<DanhMucResponse> call, Throwable t) {
                Toast.makeText(ChiTietDanhMuc.this, "Failed to update category: " + t.getMessage(), Toast.LENGTH_LONG).show();
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

        InterfaceDanhMuc interfaceDanhMuc = retrofit.create(InterfaceDanhMuc.class);
        Call<Void> call = interfaceDanhMuc.delete(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ChiTietDanhMuc.this, "Deleted category successful!", Toast.LENGTH_LONG).show();
                Return();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ChiTietDanhMuc.this, "Failed to category product: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Return() {
        Intent intent = new Intent(ChiTietDanhMuc.this, DanhMucActivity.class);
        startActivity(intent);
    }
}