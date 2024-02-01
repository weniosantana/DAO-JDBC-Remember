package org.dao.jdbc;

import org.dao.jdbc.model.entities.Department;

import java.util.Date;

public class Seller extends Department {

    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private Double baseSalary;


    public Seller(Integer id, String name) {
        super(id, name);
    }


}
