package com.nextrow.userservices.VO;

import com.nextrow.userservices.entity.Users;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseTemplateVo {
    public Department department;
    public Users users;

    public void setUser(Users users) {
        this.users=users;
    }

    public void setDepartment(Department department) {
        this.department=department;
    }
}
