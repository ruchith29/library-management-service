package com.nextrow.userservices.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Department {
    private int id;
    private String name;
    private String address;
    private int contact;
}
