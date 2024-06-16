package com.example.btweek5.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btweek5.R;
import com.example.btweek5.activity.ChiTietDanhMuc;
import com.example.btweek5.activity.ChiTietSanPham;
import com.example.btweek5.entities.DanhMuc;
import com.example.btweek5.entities.SanPham;

import java.util.List;

public class DanhMucAdapter extends BaseAdapter {
    private Context context;
    private List<DanhMuc> list;

    public DanhMucAdapter(Context context, List<DanhMuc> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_danh_muc, parent, false);
            holder = new ViewHolder();
            holder.tvMaDanhMuc = convertView.findViewById(R.id.tvMaDanhMuc);
            holder.tvTenDanhMuc = convertView.findViewById(R.id.tvTenDanhMuc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DanhMuc danhMuc = list.get(position);
        if (danhMuc != null) {
            holder.tvMaDanhMuc.setText(String.valueOf(danhMuc.getMaDanhMuc()));
            holder.tvTenDanhMuc.setText(danhMuc.getTenDanhMuc());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DanhMuc danhMuc1 = list.get(position);
                Intent intent = new Intent(context, ChiTietDanhMuc.class);
                intent.putExtra("MaDanhMuc", danhMuc1.getMaDanhMuc());
                intent.putExtra("TenDanhMuc", danhMuc1.getTenDanhMuc());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tvMaDanhMuc, tvTenDanhMuc;
    }
}
