package org.dao.jdbc;

import org.dao.jdbc.db.DB;
import org.dao.jdbc.model.dao.DaoFactory;
import org.dao.jdbc.model.dao.SellerDao;
import org.dao.jdbc.model.dao.impl.SellerDaoJDBC;
import org.dao.jdbc.model.entities.Department;
import org.dao.jdbc.model.entities.Seller;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        SellerDao sld = DaoFactory.createSellerDao();


        System.out.println("Seller FindById: ");
        Seller sl = sld.findById(3);
        System.out.println(sl);
        System.out.println();
        System.out.println("Seller FindByDepartment: ");
        Department department = new Department(2, null);
        List<Seller> list = sld.findByDepartment(department);

        for(Seller obj : list){
            System.out.println(obj);
        }
        System.out.println();
        List<Seller> ls = sld.findAll();
        System.out.println("Seller FindByAll: ");

        for(Seller obj : ls){
            System.out.println(obj);
        }
        System.out.println();

        Seller insert = new Seller(null, "Wenio", "Wenio.souza123@gmail.com", new Date(), 4000.0, department);
        sld.insert(insert);

    }
}
