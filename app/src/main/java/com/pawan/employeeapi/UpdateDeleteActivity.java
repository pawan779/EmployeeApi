package com.pawan.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pawan.employeeapi.Url.Url;
import com.pawan.employeeapi.api.EmployeeApi;
import com.pawan.employeeapi.model.Employee;
import com.pawan.employeeapi.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDeleteActivity extends AppCompatActivity {
    private EditText etEmployeeNo,etUpdateName,etUpdateSalary,etUpdateAge;
    private Button btnSearchUpdate,btnUpdate,btnDelete;
    Retrofit retrofit;
    EmployeeApi employeeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        etEmployeeNo=findViewById(R.id.etEmployeeNo);
        etUpdateName=findViewById(R.id.etUpdateName);
        etUpdateSalary=findViewById(R.id.etUpdateSalary);
        etUpdateAge=findViewById(R.id.etUpdateAge);
        btnSearchUpdate=findViewById(R.id.btnSearchUpdate);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);

        btnSearchUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                loadData();


            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
updateEmployee();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
deleteEmployee();
            }
        });
    }

    private void deleteEmployee() {
        CreateInstance();
        Call<Void> voidCall=employeeApi.deleteEmployee(Integer.parseInt(etEmployeeNo.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteActivity.this, "Sucessfully deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateEmployee() {
        EmployeeCUD employeeCUD=new EmployeeCUD(
                etUpdateName.getText().toString(),
                Float.parseFloat(etUpdateSalary.getText().toString()),
                Integer.parseInt(etUpdateAge.getText().toString())
        );
        Call<Void> voidCall=employeeApi.updateEmployee(Integer.parseInt(etEmployeeNo.getText().toString()),employeeCUD);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        if (TextUtils.isEmpty(etEmployeeNo.getText())){
            etEmployeeNo.setError("Please enter employee id");
            return;
        }
        CreateInstance();
       Call<Employee> listCall=employeeApi.getEmployeeByID(Integer.parseInt(etEmployeeNo.getText().toString()));

       listCall.enqueue(new Callback<Employee>() {
           @Override
           public void onResponse(Call<Employee> call, Response<Employee> response) {
               etUpdateName.setText(response.body().getEmployee_name());
               etUpdateSalary.setText(response.body().getEmployee_salary());
               etUpdateAge.setText(Integer.toString(response.body().getEmployee_age()));
           }

           @Override
           public void onFailure(Call<Employee> call, Throwable t) {
               Toast.makeText(UpdateDeleteActivity.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void CreateInstance() {
        retrofit=new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        employeeApi=retrofit.create(EmployeeApi.class);
    }
}
