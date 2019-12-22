package com.pawan.employeeapi.api;

import com.pawan.employeeapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApi {

    @GET("employees")
    Call<List<Employee>>getAllEmployees();
}
