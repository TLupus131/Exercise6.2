package com.example.btweek5.Interface;

import com.example.btweek5.Response.SanPhamResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceSanPham {
    @POST("/api/san_pham/insert")
    Call<SanPhamResponse> insert(@Body SanPhamResponse sanPhamResponse);

    @POST("/api/san_pham/update")
    Call<SanPhamResponse> update(@Body SanPhamResponse sanPhamResponse);

    @DELETE("/api/san_pham/delete/{maSanPham}")
    Call<Void> delete(@Path("maSanPham") int maSanPham);

}
