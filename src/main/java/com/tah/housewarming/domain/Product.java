package com.tah.housewarming.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {
    private Integer id;
    private String name;
    private String description;
    private String brand;
    private Integer count;
    private List<String> linksList;

    public Boolean isProductAvailable() {
        if(this.count == null)
            return false;

        return !(this.count <= 0);
    }
}
