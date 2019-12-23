package com.pawan.employeeapi.api;

import com.pawan.employeeapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeApi {

    @GET("employees")
    Call<List<Employee>>getAllEmployees();

    @GET("employee/{empID}")
    Call<Employee>getEmployeeByID(@Path("empID") int empID);
}
