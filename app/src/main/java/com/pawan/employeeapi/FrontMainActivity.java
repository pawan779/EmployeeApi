package com.pawan.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.pawan.employeeapi.Url.Url;
import com.pawan.employeeapi.api.EmployeeApi;
import com.pawan.employeeapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FrontMainActivity extends AppCompatActivity {
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_main);
        tvOutput=findViewById(R.id.tvOutput);


        Retrofit retrofit=new  Retrofit.Builder().baseUrl(Url.base_url).addConverterFactory(GsonConverterFactory.create()).build();

        EmployeeApi employeeApi=retrofit.create(EmployeeApi.class);


        Call<List<Employee>> listCall=employeeApi.getAllEmployees();

        //Asynchrous call

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(FrontMainActivity.this, "Error code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Employee> employeeList=response.body();

                for (Employee emp: employeeList) {
                    String data="";

                    data += "Name is : "+ emp.getEmployee_name()+"\n";
                    data += "Salary is : "+ emp.getEmployee_salary()+"\n";
                    data += "Age is : "+ emp.getEmployee_age()+"\n";

                    data += "------------------------------"+"\n";
                    tvOutput.append(data);
                }


            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Log.d("Msg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(FrontMainActivity.this,"Error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
