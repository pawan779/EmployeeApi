package com.pawan.employeeapi.api;

import com.pawan.employeeapi.model.Employee;
import com.pawan.employeeapi.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeApi {

    @GET("employees")
    Call<List<Employee>>getAllEmployees();

    @GET("employee/{empID}")
    Call<Employee>getEmployeeByID(@Path("empID") int empID);

    @POST("create")
    Call<Void>registerEmploee(@Body EmployeeCUD emp);

    //Delete Employee on the basis of EmpID
    @DELETE("delete/{empID}")
    Call<Void> deleteEmployee(@Path("empID") int empID);

    //Update Employee on the basis of EmpID
    @PUT("update/{empID}")
    Call<Void> updateEmployee(@Path("empID") int empID, @Body EmployeeCUD emp);
}
