package com.pawan.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pawan.employeeapi.Url.Url;
import com.pawan.employeeapi.api.EmployeeApi;
import com.pawan.employeeapi.model.Employee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    private EditText etEmployeeNo;
    private Button btnSearch;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etEmployeeNo=findViewById(R.id.etEmployeeNo);
        btnSearch=findViewById(R.id.btnSearch);
        tvData=findViewById(R.id.tvData);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    loadData();
}
        });
    }



    private void loadData() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeApi employeeApi=retrofit.create(EmployeeApi.class);

        Call<Employee> listCall=employeeApi.getEmployeeByID(Integer.parseInt(etEmployeeNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                String data="";
                data +="ID : "+response.body().getId()+"\n";
                data += "Name : "+response.body().getEmployee_name()+"\n";
                data += "Salary : "+response.body().getEmployee_salary()+"\n";
                data += "Age : "+response.body().getEmployee_age()+"\n";

                tvData.setText(data);
            }


            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
