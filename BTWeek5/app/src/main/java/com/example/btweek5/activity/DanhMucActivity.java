package com.example.btweek5.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btweek5.Interface.IPConfig;
import com.example.btweek5.R;
import com.example.btweek5.adapter.DanhMucAdapter;
import com.example.btweek5.entities.DanhMuc;

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


public class DanhMucActivity extends AppCompatActivity {
    private ListView listView;
    private DanhMucAdapter adapter;
    private List<DanhMuc> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Danh Mục");
        }
        listView = findViewById(R.id.lvDanhMuc);

        list = new ArrayList<>();
        adapter = new DanhMucAdapter(DanhMucActivity.this, list);
        listView.setAdapter(adapter);
        new FetchDanhMuc().execute();
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
                        DanhMuc danhMuc = new DanhMuc(ma, ten);
                        list.add(danhMuc);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_navigate);
        if (menuItem != null) {
            menuItem.setTitle("Sản phẩm");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_navigate) {
            Intent intent = new Intent(DanhMucActivity.this, SanPhamActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}