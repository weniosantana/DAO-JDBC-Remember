package org.dao.jdbc.model.dao.impl;

import com.mysql.cj.protocol.Resultset;
import org.dao.jdbc.db.DB;
import org.dao.jdbc.db.DbException;
import org.dao.jdbc.model.dao.SellerDao;
import org.dao.jdbc.model.entities.Department;
import org.dao.jdbc.model.entities.Seller;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;



    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

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

                Department dep = new Department();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));
                Seller obj = new Seller();
                obj.setId(rs.getInt("Id"));
                obj.setBaseSalary(rs.getDouble("BaseSalary"));
                obj.setEmail(rs.getString("Email"));
                obj.setName(rs.getString("Name"));
                obj.setBirthDate(rs.getDate("BirthDate"));
                obj.setDepartment(dep);
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

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
