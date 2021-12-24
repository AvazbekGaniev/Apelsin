package apelsin.controller;

import apelsin.entity.Customer;
import apelsin.payload.ApiResponse;
import apelsin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public HttpEntity<?> addCustomer(@RequestBody Customer customer) {
        ApiResponse apiResponse = customerService.addCustomer(customer);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editCustomer(@PathVariable int id, @RequestBody Customer customer) {
        ApiResponse apiResponse = customerService.editCustomer(id, customer);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        List<Customer> all = customerService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/getOne/{id}")
    public HttpEntity<?> getOne(@PathVariable int id) {
        ApiResponse apiResponse = customerService.getOneCustomer(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCustomer(@PathVariable int id) {
        ApiResponse apiResponse = customerService.deleteCustomer(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/customersWithoutOrders")
    public HttpEntity<?> getAllByNotOrder() {
        ApiResponse apiResponse = customerService.getAllByNotOrder();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getOrderedCust")
    public HttpEntity<?> getOrdered() {
        ApiResponse apiResponse = customerService.orderedCust();
        return ResponseEntity.ok(apiResponse);
    }
}
