package com.polchlopek.entity;

//import com.sun.org.apache.xpath.internal.operations.String;

import javax.persistence.*;

@Entity
@Table(name="meas_category")
public class MeasurementCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="category")
    private String category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "MeasurementCategory{" +
                "category=" + category +
                '}';
    }
}
