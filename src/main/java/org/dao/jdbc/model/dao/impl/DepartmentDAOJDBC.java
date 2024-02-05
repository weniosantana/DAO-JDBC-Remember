package org.dao.jdbc.model.dao.impl;

import org.dao.jdbc.db.DbException;
import org.dao.jdbc.model.dao.DeparmentDao;
import org.dao.jdbc.model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDAOJDBC implements DeparmentDao {
    private Connection conn;

    public DepartmentDAOJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st;

        try{
            st = conn.prepareStatement("INSERT INTO department (Id, Name) VALUES(?,?)");

            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());
            st.executeUpdate();


        } catch (SQLException e) {
            throw new DbException("Erro no Insert do Department");
        }
    }

    @Override
    public void update(Integer id, Department obj) {
        PreparedStatement st;

        try{

            conn.setAutoCommit(false);
            st = conn.prepareStatement("UPDATE department SET Id = ?, Name = ? WHERE Id = ?");
            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());

            st.setInt(3, id);
            st.executeUpdate();

            conn.commit();
        }catch(SQLException e){

            try{
                conn.rollback();
            }catch(SQLException f){
                f.printStackTrace();
            }
            throw new DbException("Erro no Update do Department");
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}
