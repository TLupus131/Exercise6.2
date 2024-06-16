package com.example.btweek5.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btweek5.R;
import com.example.btweek5.activity.ChiTietSanPham;
import com.example.btweek5.entities.SanPham;

import java.util.List;

public class SanPhamAdapter extends BaseAdapter {

    private Context context;
    private List<SanPham> list;

    public SanPhamAdapter(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_san_pham, parent, false);
            holder = new ViewHolder();
            holder.tvMa = convertView.findViewById(R.id.tvMa);
            holder.tvTen = convertView.findViewById(R.id.tvTen);
            holder.tvDonGia = convertView.findViewById(R.id.tvDonGia);
            holder.tvDanhMuc = convertView.findViewById(R.id.tvDanhMuc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SanPham sanPham = list.get(position);
        if (sanPham != null) {
            holder.tvMa.setText(String.valueOf(sanPham.getMa()));
            holder.tvTen.setText(sanPham.getTen());
            holder.tvDonGia.setText(String.valueOf(sanPham.getDonGia()));
            holder.tvDanhMuc.setText(sanPham.getTenDanhMuc());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham1 = list.get(position);
                Intent intent = new Intent(context, ChiTietSanPham.class);
                intent.putExtra("Ma", sanPham1.getMa());
                intent.putExtra("Ten", sanPham1.getTen());
                intent.putExtra("DonGia", sanPham1.getDonGia());
                intent.putExtra("TenDanhMuc", sanPham1.getTenDanhMuc());

                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tvMa, tvTen, tvDonGia, tvDanhMuc;
    }
}
