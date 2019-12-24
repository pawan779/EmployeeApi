package com.pawan.employeeapi.Url;

import com.pawan.employeeapi.api.EmployeeApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {

    public static final String base_url="http://dummy.restapiexample.com/api/v1/";

    public static Retrofit createInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
