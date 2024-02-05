package org.dao.jdbc;

import org.dao.jdbc.db.DB;
import org.dao.jdbc.model.dao.DaoFactory;
import org.dao.jdbc.model.dao.SellerDao;
import org.dao.jdbc.model.dao.impl.SellerDaoJDBC;
import org.dao.jdbc.model.entities.Seller;

import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SellerDao sld = DaoFactory.createSellerDao();


        System.out.println("Seller FindById: ");
        Seller sl = sld.findById(3);

        System.out.println(sl);


    }
}
