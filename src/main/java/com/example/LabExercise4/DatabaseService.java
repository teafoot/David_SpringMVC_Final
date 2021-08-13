package com.example.LabExercise4;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService implements DatabaseInterface {
    Connection javaConnection;

    public DatabaseService(Connection javaConnection) {
        this.javaConnection = javaConnection;
    }

    @Override
    public void addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO savingstable VALUES ( ?, ?, ?, ?, ? )";
        PreparedStatement preparedStatement = javaConnection.prepareStatement(query);
        preparedStatement.setString(1, customer.getCustomerNumber());
        preparedStatement.setString(2, customer.getCustomerName());
        preparedStatement.setDouble(3, customer.getCustomerInitialDeposit());
        preparedStatement.setInt(4, customer.getNumberOfYears());
        preparedStatement.setString(5, customer.getSavingsType());
        preparedStatement.executeUpdate();
    }

    @Override
    public Customer editCustomer(Customer newCustomer, String oldCusNo) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement;
        preparedStatement = javaConnection.prepareStatement("Update savingstable set custname=?, cdep=?, nyears=?, savtype=? where custno = ?");
        preparedStatement.setString(1, newCustomer.getCustomerName());
        preparedStatement.setDouble(2, newCustomer.getCustomerInitialDeposit());
        preparedStatement.setInt(3, newCustomer.getNumberOfYears());
        preparedStatement.setString(4, newCustomer.getSavingsType());
        preparedStatement.setString(5, oldCusNo);
        preparedStatement.executeUpdate();
        return newCustomer;
    }

    @Override
    public void deleteCustomer(String cusNo) throws SQLException {
        String query = "Delete from savingstable where custno = ?";
        PreparedStatement preparedStatement = javaConnection.prepareStatement(query);
        preparedStatement.setString(1, cusNo);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Customer> getCustomers() throws ClassNotFoundException, SQLException {
        String query = "Select * from savingstable";
        PreparedStatement preparedStatement = javaConnection.prepareStatement(query);

        List<Customer> customerList = new ArrayList<>();
        Customer customer;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            customer = new Customer(
                    resultSet.getString("custno"),
                    resultSet.getString("custname"),
                    resultSet.getDouble("cdep"),
                    resultSet.getInt("nyears"),
                    resultSet.getString("savtype")
            );
            customerList.add(customer);
        }
        return customerList;
    }

    public Customer searchCustomer(String custNo) throws SQLException {
        String query = "Select * from savingstable where custno = ?";
        PreparedStatement preparedStatement = javaConnection.prepareStatement(query);
        preparedStatement.setString(1, custNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.first()) {
            System.out.print("Record not existing");
            return null;
        }
        return new Customer(
                resultSet.getString("custno"),
                resultSet.getString("custname"),
                resultSet.getDouble("cdep"),
                resultSet.getInt("nyears"),
                resultSet.getString("savtype")
        );
    }



/////////////////////////////////////////////////////




        @Override
    public void addCategory(Category category) throws SQLException {
        String query = "INSERT INTO category VALUES ( ?, ? )";
        PreparedStatement preparedStatement = javaConnection.prepareStatement(query);
        preparedStatement.setString(1, category.getCatCode());
        preparedStatement.setString(2, category.getCatDesc());
        preparedStatement.executeUpdate();
    }

    @Override
    public Category editCategory(Category newCategory, String oldCatCode) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = javaConnection.prepareStatement("Update category set catcode=?, catdesc=? where catcode = ?");
        preparedStatement.setString(1, newCategory.getCatCode());
        preparedStatement.setString(2, newCategory.getCatDesc());
        preparedStatement.setString(3, oldCatCode);
        preparedStatement.executeUpdate();
        return newCategory;
    }

    @Override
    public void deleteCategory(String catCode) throws SQLException {
        String query = "Delete from Category where catcode = ?";
        PreparedStatement preparedStatement = javaConnection.prepareStatement(query);
        preparedStatement.setString(1, catCode);
        preparedStatement.executeUpdate();
    }

    public Category search(String catCode) throws SQLException {
        String query = "Select * from category where catcode = ?";
        PreparedStatement preparedStatement = javaConnection.prepareStatement(query);
        preparedStatement.setString(1, catCode);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.first()) {
            System.out.print("Record not existing");
            return null;
        }
        return new Category(resultSet.getString("catcode"), resultSet.getString("catdesc"));
    }

    @Override
    public List<Category> getCategories() throws SQLException { //create an array list that will contain the data recovered
        String query = "Select * from category";
        PreparedStatement preparedStatement = javaConnection.prepareStatement(query);

        List<Category> categoryList = new ArrayList<>();
        Category category;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            category = new Category(resultSet.getString("catcode"), resultSet.getString("catdesc"));
            categoryList.add(category);
        }
        return categoryList;
    }

    public List<Item> getItems(String catCode) throws ClassNotFoundException, SQLException { //create an array list that will contain the data recovered
        String query = "Select itemcode,itemdesc from items where catcode=?";
        PreparedStatement preparedStatement = javaConnection.prepareStatement(query);
        preparedStatement.setString(1, catCode);

        List<Item> itemList = new ArrayList<>();
        Item item;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            item = new Item(resultSet.getString("itemcode"), resultSet.getString("itemdesc"));
            itemList.add(item);
        }
        return itemList;
    }
}

//package com.example.LabExercise4;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseService implements DatabaseInterface {
//    Connection javaConnection;
//
//    public DatabaseService(Connection javaConnection) {
//        this.javaConnection = javaConnection;
//    }
//

//}