package org.dao.jdbc.model.dao.impl;

import org.dao.jdbc.db.DbException;
import org.dao.jdbc.model.dao.DeparmentDao;
import org.dao.jdbc.model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

        PreparedStatement st;

        try{

            conn.setAutoCommit(false);
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();

            conn.commit();




        }catch(SQLException e){
            try{
                conn.rollback();
            }catch(SQLException f){
                f.printStackTrace();
            }
            throw new DbException("Erro no delete de Department");
        }


    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st;
        ResultSet rs;

        try{
            st = conn.prepareStatement("Select Id, name from department Where Id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();

            while(rs.next()){

                System.out.println("Id: " + rs.getString("Id") + " \nName: "  + rs.getString("name"));

            }

        }catch (SQLException e ){
            throw new DbException("Erro no findById");
        }

        return null;
    }

    @Override
    public List<Department> findAll() {

        PreparedStatement st;
        ResultSet rs;

        try{
            st = conn.prepareStatement("Select * from department");
            rs = st.executeQuery();

            List<Department> ls = new ArrayList<>();
            while(rs.next()){

                Integer id = rs.getInt("Id");
                String Name = rs.getString("Name");
                Department dp = new Department(id, Name);

                ls.add(dp);
            }
            return ls;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
