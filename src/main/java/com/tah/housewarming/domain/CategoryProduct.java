package com.tah.housewarming.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CategoryProduct {
    @Id
    private Integer id;
    private Integer categoryId;
    private Integer productId;
}
