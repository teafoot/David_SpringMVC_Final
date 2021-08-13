package com.example.LabExercise4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"errorMessage", "errorMessageSession"}) // for the model
@RequestMapping
@Controller
public class CustomerController {
    DatabaseService databaseService;

    public CustomerController() throws SQLException, ClassNotFoundException {
        Connection123 connection123 = new Connection123();
        databaseService = new DatabaseService(connection123.connect());
    }

    @RequestMapping(value = {"/","/list-customers"}, method = RequestMethod.GET)
    public String listCustomers(ModelMap model) throws ClassNotFoundException, SQLException {
        if (model.get("errorMessageSession") != "") {
            model.put("errorMessage", model.get("errorMessageSession"));
            model.put("errorMessageSession", "");
        } else {
            model.put("errorMessage", "");
        }
        List<Customer> customers = databaseService.getCustomers();
        model.addAttribute("customers", customers);

        return "customer-list";
    }

    @RequestMapping(value = "/add-customer", method = RequestMethod.GET)
    public String addCustomerPage() {
        return "customer-add";
    }

    @RequestMapping(value = "/add-customer", method = RequestMethod.POST)
    public String addCustomer(ModelMap model, @RequestParam String cusNo, @RequestParam String customerName, @RequestParam Double customerInitialDeposit, @RequestParam int numberYears, @RequestParam String savingsType) throws SQLException, ClassNotFoundException {
        if (databaseService.searchCustomer(cusNo) != null) {
            model.put("errorMessageSession", "The record you are trying to add is already existing. Choose a different customer number.");
            return "redirect:/";
        }
        model.put("errorMessageSession", "");
        Customer customer = new Customer(
            cusNo, customerName, customerInitialDeposit, numberYears, savingsType
        );
        databaseService.addCustomer(customer);
        return "redirect:/";
    }

    @RequestMapping(value = "/update-customer", method = RequestMethod.GET)
    public String updateCustomerPage(ModelMap model, @RequestParam(defaultValue = "") String id) throws SQLException {
        Customer foundCustomer = databaseService.searchCustomer(id);
        model.addAttribute("customer", foundCustomer); //
        return "customer-edit";
    }

    @RequestMapping(value = "/update-customer", method = RequestMethod.POST)
    public String updateCustomer(ModelMap model, @RequestParam String cusNo, @RequestParam String customerName, @RequestParam Double customerInitialDeposit, @RequestParam int numberYears, @RequestParam String savingsType) throws SQLException, ClassNotFoundException {
        Customer newCustomer = new Customer(
            cusNo, customerName, customerInitialDeposit, numberYears, savingsType
        );
        databaseService.editCustomer(newCustomer, cusNo);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete-customer", method = RequestMethod.GET)
    public String deleteCustomer(ModelMap model, @RequestParam String id) throws SQLException {
        databaseService.deleteCustomer(id);
        model.clear();
        return "redirect:/";
    }

    @RequestMapping(value = "/projected-investment-customer", method = RequestMethod.GET)
    public String projectedInvestmentCustomerPage(ModelMap model, @RequestParam(defaultValue = "") String id) throws SQLException {
        Customer foundCustomer = databaseService.searchCustomer(id);
        model.addAttribute("customer", foundCustomer); //

        int year = foundCustomer.getNumberOfYears();
        double starting = foundCustomer.getCustomerInitialDeposit();//accumulated
        double interest;
        double ending;//accumulated+interest
        for (int i = 0 ; i< year; i++) {
            foundCustomer.year.add(i+1);
            foundCustomer.starting.add(starting);
            interest = ((foundCustomer.getSavingsType() == "savings-deluxe") ? 0.15 : 0.10) * starting;
            ending = starting + interest;//accumulated amount+interest
            starting = ending;
            foundCustomer.interest.add(interest);
            foundCustomer.ending.add(ending);
        }

//        List<List<Double>> numberslist = new ArrayList<>();
//        numberslist.add(foundCustomer.starting);
//        numberslist.add(foundCustomer.interest);
//        numberslist.add(foundCustomer.ending);
//        model.put("numbers", numberslist);

        model.put("year", foundCustomer.year);
        model.put("starting", foundCustomer.starting);
        model.put("interest", foundCustomer.interest);
        model.put("ending", foundCustomer.ending);

        return "customer-projected-investment";
    }
}
