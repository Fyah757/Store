package org.fyahskuztum.ecommerce.store.controller;

import jakarta.validation.Valid;
import org.fyahskuztum.ecommerce.store.model.Customer;
import org.fyahskuztum.ecommerce.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String displayCustomerPage(Model model) {
        System.out.println("===========>IN displayCustomerPage() ");
        List<Customer> allCustList = customerService.getAllCustomers();
        model.addAttribute("custList", allCustList);
        return "customers";
    }

    @GetMapping("/customers/create")
    public String createCustomerForm(Model model) {
        System.out.println("===========>IN createCustomerForm() ");
        model.addAttribute("cust", new Customer());
        return "newCustomerForm";
    }

    @PostMapping("/customers")
    public String addNewCustomer(@ModelAttribute("cust") @Valid Customer cust, BindingResult result) {
        System.out.println("===========>IN addNewCustomer() ");

        Optional<Customer> existingCust = customerService.getCustomerByEmail(cust.getEmail());

        if (existingCust.isPresent()){
            result.rejectValue("email", null, "There is already an existing customer with that email");
        }

        if(result.hasErrors()) {
            return "newCustomerForm";
        }

        customerService.addNewCustomer(cust);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{custId}")
    public String updateCustomerForm(Model model, @PathVariable Long custId) {
        System.out.println("===========>IN updateCustomerForm() ");
        model.addAttribute("cust", customerService.getCustomerById(custId).get());
        return "editCustomerForm";
    }

    @PostMapping("/customers/edit")
    public String updateCustomer(@ModelAttribute("cust") @Valid Customer custUpdate, BindingResult result) {
        System.out.println("===========>IN updateCustomer() ");

        Customer existingCustomerById = customerService.getCustomerById(custUpdate.getId()).get();

        // if customer email is changed - check if the new email already exist
        if(!existingCustomerById.getEmail().equals(custUpdate.getEmail())) {
            Optional<Customer> existingCustomer = customerService.getCustomerByEmail(custUpdate.getEmail());

            if(existingCustomer.isPresent()) {
                result.rejectValue("email", null, "There is already an existing customer with that email");
            }
        }

        if(result.hasErrors()) {
            return "editCustomerForm";
        }

        customerService.updateCustomer(custUpdate);
        return "redirect:/customers";
    }

    @GetMapping("/customers/delete/{custId}")
    public String deleteCustomer(@PathVariable Long custId) {
        System.out.println("===========>IN deleteCustomer() ");
        customerService.deleteCustomerById(custId);
        return "redirect:/customers";
    }

}


