package com.smthweird.idillikajava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<CatalogItem> catalogList;
    private CatalogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        toolbar = findViewById(R.id.catalog_activity_Toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        initRecyclerView();

        App.getIdillikaApi().getCatalogList(21, "f3e82db3d0b2bcce07eae17dd9cb46d3").enqueue(new Callback<List<CatalogItem>>() {
            @Override
            public void onResponse(Call<List<CatalogItem>> call, Response<List<CatalogItem>> response) {
                if (response.isSuccessful()) {
                    catalogList = response.body();
                    adapter = new CatalogAdapter(catalogList);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<CatalogItem>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Проверьте подключение к сети", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    public void initRecyclerView() {
        recyclerView = findViewById(R.id.catalog_activity_RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.basket_button) {
            Intent intent = new Intent(CatalogActivity.this, BasketActivity.class);
            startActivity(intent);
        }
        return true;
    }
}