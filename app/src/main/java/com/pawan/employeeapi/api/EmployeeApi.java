package com.pawan.employeeapi.api;

import com.pawan.employeeapi.model.Employee;
import com.pawan.employeeapi.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeApi {

    @GET("employees")
    Call<List<Employee>>getAllEmployees();

    @GET("employee/{empID}")
    Call<Employee>getEmployeeByID(@Path("empID") int empID);

    @POST("create")
    Call<Void>registerEmploee(@Body EmployeeCUD emp);
}
