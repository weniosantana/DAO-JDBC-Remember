package org.dao.jdbc.model.dao.impl;

import com.mysql.cj.protocol.Resultset;
import org.dao.jdbc.db.DB;
import org.dao.jdbc.db.DbException;
import org.dao.jdbc.model.dao.SellerDao;
import org.dao.jdbc.model.entities.Department;
import org.dao.jdbc.model.entities.Seller;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;



    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        try{
            conn.setAutoCommit(false);
            st = conn.prepareStatement("INSERT INTO seller" +
                    " (Name, Email, BirthDate, BaseSalary, DepartmentId)" +
                    " VALUES" +
                    " (?, ?, ?, ?, ?)");
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4,obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            st.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            try {
                conn.rollback();
            }catch(SQLException f){
                f.printStackTrace();
            }
            e.printStackTrace();
            throw new DbException("Erro ao executar o Insert");
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Seller obj) {
        PreparedStatement st = null;


        try{
            conn.setAutoCommit(false);

            st = conn.prepareStatement("UPDATE seller" +
                    " SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ?" +
                    " WHERE Id = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4,obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            st.setInt(6, obj.getId());
            st.executeUpdate();

            conn.commit();
        }catch (SQLException e){
            try {
                conn.rollback();
            }catch(SQLException f){
                f.printStackTrace();
            }
            e.printStackTrace();
            throw new DbException("Erro ao executar o Update");
        }
        finally {
            DB.closeStatement(st);


        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        try{
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName" +
                    " FROM seller INNER JOIN department" +
                    " ON seller.DepartmentId = department.Id" +
                    " WHERE seller.Id = ?");

            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()){

                Department dep = instantiateDepartment(rs);


                Seller obj = instantiateSeller(rs, dep);

                return obj;
            }
            return null;

        }catch (SQLException e){
            e.printStackTrace();
            throw new DbException("Erro ao executar o findById");
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }

    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException{


        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setEmail(rs.getString("Email"));
        obj.setName(rs.getString("Name"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException{

        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));

        return dep;

    }

    @Override
    public List<Seller> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        try{
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName" +
                    " FROM seller INNER JOIN department" +
                    " ON seller.DepartmentId = department.Id" +
                    " ORDER BY Name");

            rs = st.executeQuery();


            List<Seller> list = new ArrayList<>();
            while(rs.next()){


                Department dep;
                dep = instantiateDepartment(rs);


                Seller obj = instantiateSeller(rs, dep);

                list.add(obj);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
            throw new DbException("Erro ao executar o FindByAll");
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }


    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        try{
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName" +
                    " FROM seller INNER JOIN department" +
                    " ON seller.DepartmentId = department.Id" +
                    " WHERE DepartmentId = ?" +
                    " ORDER BY Name");

            st.setInt(1, department.getId());
            rs = st.executeQuery();


            List<Seller> list = new ArrayList<>();

            Map<Integer,Department> map = new HashMap<>();

            while(rs.next()){


                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller obj = instantiateSeller(rs, dep);

                list.add(obj);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
            throw new DbException("Erro ao executar o FindByDepartment");
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }



    }
}
