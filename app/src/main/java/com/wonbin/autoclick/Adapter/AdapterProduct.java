package com.wonbin.autoclick.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wonbin.autoclick.Models.Product;
import com.wonbin.autoclick.R;

import java.util.List;

public class AdapterProduct extends ArrayAdapter<Product> {

    Activity context;
    int resource;
    List<Product> objects;

    public AdapterProduct(@NonNull Activity context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        row = layoutInflater.inflate(this.resource, null);
        // lấy các thành phần từ view_nation
        ImageView _imgProduct = row.findViewById(R.id.img_Product);
        TextView _nameProduct = row.findViewById(R.id.txt_name_Product);
        TextView _priceProduct = row.findViewById(R.id.txt_price_Product);
        TextView _soleProduct = row.findViewById(R.id.txt_sole_Product);
        // trả về quốc gia hiện tại muốn vẽ
        Product product = this.objects.get(position);
        _imgProduct.setImageResource(product.getImg());
        _nameProduct.setText(product.getName_Product());
        _priceProduct.setText("đ" + String.valueOf(product.getPrice()));
        _soleProduct.setText("Đã bán " + String.valueOf(product.getSole()));

        // nhấp vào hiển thị bằng Toast
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Bạn chọn: " + product.getName_Product(), Toast.LENGTH_SHORT).show();
                // Activity context || Context context -> dùng để xác định nơi hiển thị 1 chức năng
                // duration: the time during which something continues.(khoảng thời gian)
            }
        });
        return row;
    }
}
