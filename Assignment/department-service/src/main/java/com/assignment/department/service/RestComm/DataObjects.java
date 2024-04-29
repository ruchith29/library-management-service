package com.assignment.department.service.RestComm;

import com.assignment.department.service.entity.DepartmentEntity;
import lombok.*;

@Data
@Setter
@Getter
public class DataObjects {
    private Employee employee;
    private DepartmentEntity departmentEntity;
    String Value="No change in Salary";

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    public void getString(String s) {
        this.Value=s;
    }
}
