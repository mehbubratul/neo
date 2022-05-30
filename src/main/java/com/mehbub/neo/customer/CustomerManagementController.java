package com.mehbub.neo.customer;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/api/v1/customers")
public class CustomerManagementController {
    
    private List<Customer> Customers = Arrays.asList(
        new Customer(1,"Kitty", "Mr. Jashim", "Savar", true),
        new Customer(2,"Ericson", "Mr. Kamal", "Keraniganj", true),
        new Customer(3,"Polash", "Mr. Morshed", "SHampur", true)
        ); 

    @GetMapping("/")
    public List<Customer> getCustomers() {
        return Customers;
    }

    @PostMapping()
    public void registerCustomer(@RequestBody Customer customer){
        System.out.println(customer);
    }

    @DeleteMapping(path = "/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId){
        System.out.println(customerId);
    }

    @PutMapping(path = "/{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer customerId, @RequestBody Customer customer){
        System.out.println(String.format("%s %s", customerId, customer.getCustomerName()));
    }
}
