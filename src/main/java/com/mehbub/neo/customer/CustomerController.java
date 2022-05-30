package com.mehbub.neo.customer;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private List<Customer> Customers = Arrays.asList(
        new Customer(1,"Kitty", "Mr. Jashim", "Savar", true),
        new Customer(2,"Ericson", "Mr. Kamal", "Keraniganj", true),
        new Customer(3,"Polash", "Mr. Morshed", "SHampur", true)
        ); 

    @GetMapping(path = "/{customerId}")
    public Customer getCustomers(@PathVariable("customerId") Integer customerId){        
        return Customers.stream()
            .filter(cus -> customerId.equals(cus.getCustomerId()))
            .findFirst()
            .orElseThrow( () -> new IllegalStateException("Customer "+ customerId + " does not exists."));
    }
}
