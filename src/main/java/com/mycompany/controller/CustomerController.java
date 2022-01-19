package com.mycompany.controller;

import com.mycompany.exception.ResourseNotFoundException;
import com.mycompany.model.Customer;
import com.mycompany.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class CustomerController {
    @Autowired
    CustomerRepository repository;

    @DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Integer id) throws ResourseNotFoundException {

        Customer customer = repository.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("Customer is not found by id" + id)
        );

        repository.delete(customer);

        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", true);

        return response;
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer){
        return repository.save(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getBookById(@PathVariable(value = "id") Integer id)
            throws ResourseNotFoundException {

        Customer customer = repository.findById(id).orElseThrow(
                ()-> new ResourseNotFoundException("Customer is not found by id" + id)
        );

        return ResponseEntity.ok().body(customer);
    }

    @PutMapping("/customers/{id}")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Integer id)
            throws ResourseNotFoundException {

        return repository.findById(id)
                .map(customer -> {
                    customer.setLastName (newCustomer.getLastName());
                    customer.setDistrict (newCustomer.getDistrict());
                    customer.setDiscount(newCustomer.getDiscount());
                    return repository.save(customer);
                })
                .orElseThrow(
                        ()-> new ResourseNotFoundException("Customer is not found by id" + id)
                );
    }
}
