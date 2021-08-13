package com.example.LabExercise4;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseInterface {
    void addCustomer(Customer customer) throws ClassNotFoundException, SQLException;
    Customer editCustomer(Customer newCustomer, String oldCusNo) throws SQLException, ClassNotFoundException;
    void deleteCustomer(String cusNo) throws SQLException;
    List<Customer> getCustomers() throws ClassNotFoundException, SQLException;

    void addCategory(Category category) throws ClassNotFoundException, SQLException;
    Category editCategory(Category newCategory, String oldCatCode) throws SQLException, ClassNotFoundException;
    void deleteCategory(String catCode) throws SQLException;
    List<Category> getCategories() throws ClassNotFoundException, SQLException;
}