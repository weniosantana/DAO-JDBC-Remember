package org.dao.jdbc.model.entities;

import org.dao.jdbc.model.entities.Department;

import java.io.Serializable;
import java.util.Date;

public class Seller implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private Double baseSalary;





    private Department department;
    public Seller(){}
    public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary) {
        this.id =id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", baseSalary=" + baseSalary +
                '}';
    }
}
