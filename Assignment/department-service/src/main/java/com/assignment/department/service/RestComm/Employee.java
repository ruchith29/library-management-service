package com.assignment.department.service.RestComm;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Employee {
    private int emp_id;
    private String emp_name;
    private String emp_dept;
    private int date_of_join;

    public int getDate_of_join() {
        return date_of_join;
    }

    public void setDate_of_join(int date_of_join) {
        this.date_of_join = date_of_join;
    }

    public String getName() {
        return emp_name;
    }
}
