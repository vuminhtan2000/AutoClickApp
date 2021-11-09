package com.wonbin.autoclick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.wonbin.autoclick.Adapter.AdapterProduct;
import com.wonbin.autoclick.Models.Product;

import java.util.ArrayList;

/**
 * Created by wilburnLee on 2019/4/22.
 */
public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Product> listProduct;
    AdapterProduct adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        // 1. tạo view
        gridView = findViewById(R.id.gridView_Product);
        // 2. tạo database
        listProduct = new ArrayList<>();
        listProduct.add(new Product(R.drawable.anh, "Anh", 100, 100, 100 ));
        listProduct.add(new Product(R.drawable.phap, "Pháp", 1000, 1000, 1000));
        listProduct.add(new Product(R.drawable.vietnam, "Việt Nam", 10000, 10000, 10000));
        listProduct.add(new Product(R.drawable.lao, "Lào", 100000, 100000, 100000));
        // 3. tạo adapter
        adapterProduct = new AdapterProduct(this, R.layout.view_product, listProduct);
        // 4. nối database và view bằng adapter
        gridView.setAdapter(adapterProduct);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sản phẩm có khuyến mãi");
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sanPham:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.autoClick:
                Intent intent1 = new Intent(this, Main_click_swipe.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
