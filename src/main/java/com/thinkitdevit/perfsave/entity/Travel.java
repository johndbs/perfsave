package com.thinkitdevit.perfsave.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Travel implements Persistable<Integer> {

    @Id
    private Integer id;

    private String destination;

    private Integer category;

    @Transient
    private boolean isNew;

}
