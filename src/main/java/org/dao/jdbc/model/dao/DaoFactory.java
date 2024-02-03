package org.dao.jdbc.model.dao;

import org.dao.jdbc.db.DB;
import org.dao.jdbc.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());

    }

}
