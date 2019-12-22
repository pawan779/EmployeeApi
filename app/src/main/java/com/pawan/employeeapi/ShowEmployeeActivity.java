package com.pawan.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.pawan.employeeapi.RecycleView.EmployeeAdapter;
import com.pawan.employeeapi.RecycleView.EmployeeRecycle;
import com.pawan.employeeapi.Url.Url;
import com.pawan.employeeapi.api.EmployeeApi;
import com.pawan.employeeapi.model.Employee;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowEmployeeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<EmployeeRecycle> employeeRecycleList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);
        recyclerView=findViewById(R.id.recyclerView);


        Retrofit retrofit=new  Retrofit.Builder().baseUrl(Url.base_url).addConverterFactory(GsonConverterFactory.create()).build();

        EmployeeApi employeeApi=retrofit.create(EmployeeApi.class);


        Call<List<Employee>> listCall=employeeApi.getAllEmployees();

        //Asynchrous call

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(ShowEmployeeActivity.this, "Error code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Employee> employeeList=response.body();

                for (Employee emp: employeeList) {

                    employeeRecycleList.add(new EmployeeRecycle(emp.getId(),emp.getEmployee_name(),emp.getEmployee_salary(),emp.getEmployee_age()));


//                    String data="";
//
//                    data += "Name is : "+ emp.getEmployee_name()+"\n";
//                    data += "Salary is : "+ emp.getEmployee_salary()+"\n";
//                    data += "Age is : "+ emp.getEmployee_age()+"\n";
//
//                    data += "------------------------------"+"\n";
//                    tvOutput.append(data);
                }


            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Log.d("Msg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(ShowEmployeeActivity.this,"Error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        EmployeeAdapter employeeAdapter=new EmployeeAdapter(this,employeeRecycleList);
        recyclerView.setAdapter(employeeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
