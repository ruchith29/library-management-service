package com.bookmyshow.show.connection;

import com.bookmyshow.show.entity.ShowEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DAO {

    public ShowEntity showEntity;


    public void setShowEntity(ShowEntity showEntity) {
        this.showEntity = showEntity;
    }
}