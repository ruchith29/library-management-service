package com.nextrow.userservices.repository;

import com.nextrow.userservices.VO.ResponseTemplateVo;
import com.nextrow.userservices.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserId(int user_id);

}