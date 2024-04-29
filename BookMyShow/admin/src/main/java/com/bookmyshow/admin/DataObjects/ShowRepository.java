package com.bookmyshow.admin.DataObjects;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity,String> {
}
