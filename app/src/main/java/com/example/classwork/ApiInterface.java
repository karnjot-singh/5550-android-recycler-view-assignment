package com.example.classwork;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface ApiInterface {

    @GET("/hotels")
    public void getHotelsList(Callback<List<HotelListData>> callback);
}
