package org.dao.jdbc.model.dao;


import org.dao.jdbc.model.entities.Department;
import org.dao.jdbc.model.entities.Seller;

import java.util.List;

public interface SellerDao {

    void insert(Seller obj);
    void update(Seller obj);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findAll();

}
