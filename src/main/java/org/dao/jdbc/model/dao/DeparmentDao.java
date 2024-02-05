package org.dao.jdbc.model.dao;

import org.dao.jdbc.model.entities.Department;

import java.util.List;

public interface DeparmentDao {

    void insert(Department obj);
    void update(Integer id, Department obj);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();


}
