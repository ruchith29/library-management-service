package com.bookmyshow.show.repository;

import com.bookmyshow.show.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity,String> {
}
