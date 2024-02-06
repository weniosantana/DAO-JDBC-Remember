package org.dao.jdbc;

import org.dao.jdbc.db.DB;
import org.dao.jdbc.model.dao.DaoFactory;
import org.dao.jdbc.model.dao.SellerDao;
import org.dao.jdbc.model.dao.DeparmentDao;
import org.dao.jdbc.model.dao.impl.DepartmentDAOJDBC;
import org.dao.jdbc.model.dao.impl.SellerDaoJDBC;
import org.dao.jdbc.model.entities.Department;
import org.dao.jdbc.model.entities.Seller;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        SellerDao sld = DaoFactory.createSellerDao();
        DeparmentDao deparmentDao = DaoFactory.createDepartmentDao();
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
        System.out.println("Inserindo um novo Seller");
        Seller insert = new Seller(null, "Wenio", "Wenio.souza123@gmail.com", new Date(), 4000.0, department);
        sld.insert(insert);
        System.out.println("Seller inserido");

        System.out.println("Atualizando Seller");
        Seller update = new Seller(7, "Greg", "Greg@gmail.com", new Date(), 3000.0, department);
        sld.update(update);
        System.out.println("Atualizado Seller");

        System.out.println("Deletando Seller");
        sld.deleteById(5);
        System.out.println("Seller Deletado");



        //Deparment

        //Inserindo no department
        System.out.println("Inserindo um novo department");
        Department DepInsert = new Department(6, "TI");
        deparmentDao.insert(DepInsert);
        System.out.println("Inserido um novo Department");

        //Update em Department
        System.out.println("Atualizar Department");
        Department DepUpdate = new Department(6, "Prodesp");
        deparmentDao.update(8, DepUpdate);
        System.out.println("Atualizado um Department");
       
        //Delete em Department
        System.out.println("Deletando department");
        deparmentDao.deleteById(5);
        System.out.println("Department deletado");

        System.out.println("Resultado findById: ");
        deparmentDao.findById(3);
        List<Department> lis = deparmentDao.findAll();
        System.out.println("Department FindByAll: ");
        for (Department obje : lis) {
            System.out.println(obje);
        }
        System.out.println();

    }

}
