package com.example.btweek5.Interface;

import com.example.btweek5.Response.DanhMucResponse;
import com.example.btweek5.Response.SanPhamResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceDanhMuc {
    @POST("/api/danh_muc/insert")
    Call<DanhMucResponse> insert(@Body DanhMucResponse danhMucResponse);

    @POST("/api/danh_muc/update")
    Call<DanhMucResponse> update(@Body DanhMucResponse danhMucResponse);

    @DELETE("/api/danh_muc/delete/{maDanhMuc}")
    Call<Void> delete(@Path("maDanhMuc") int maDanhMuc);

}
