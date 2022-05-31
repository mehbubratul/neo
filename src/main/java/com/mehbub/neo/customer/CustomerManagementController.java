package com.mehbub.neo.customer;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public List<Customer> getCustomers() {
        return Customers;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('customer:write')")
    public void registerCustomer(@RequestBody Customer customer){
        System.out.println("Register Customer - API: /management/api/v1/customers");
        System.out.println(customer);
    }

    @DeleteMapping(path = "/{customerId}")
    @PreAuthorize("hasAuthority('customer:write')")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId){
        System.out.println("Delete Customer - API: /management/api/v1/customers");
        System.out.println(customerId);
    }

    @PutMapping(path = "/{customerId}")
    @PreAuthorize("hasAuthority('customer:write')")
    public void updateCustomer(@PathVariable("customerId") Integer customerId, @RequestBody Customer customer){
        System.out.println("Update Customer - API: /management/api/v1/customers");
        System.out.println(String.format("%s %s", customerId, customer.getCustomerName()));
    }
}
