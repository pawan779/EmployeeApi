package com.pawan.employeeapi.RecycleView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pawan.employeeapi.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    Context context;
    List<EmployeeRecycle> employeeRecycleList;

    public EmployeeAdapter(Context context, List<EmployeeRecycle> employeeRecycleList) {
        this.context = context;
        this.employeeRecycleList = employeeRecycleList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_layout_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.EmployeeViewHolder holder, int position) {
        EmployeeRecycle employeeRecycle = employeeRecycleList.get(position);
        holder.tvId.setText(employeeRecycle.getEmployeeId()+"");
        holder.tvName.setText(employeeRecycle.getEmployeeName());
        holder.tvSalary.setText(employeeRecycle.getEmployeeSalary());
        holder.tvAge.setText(employeeRecycle.getEmployeeAge()+"");
    }

    @Override
    public int getItemCount() {
        return employeeRecycleList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvSalary, tvAge;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvEmployeeId);
            tvName = itemView.findViewById(R.id.tvEmployeeName);
            tvSalary = itemView.findViewById(R.id.tvEmployeeSalary);
            tvAge = itemView.findViewById(R.id.tvEmployeeAge);
        }
    }
}
