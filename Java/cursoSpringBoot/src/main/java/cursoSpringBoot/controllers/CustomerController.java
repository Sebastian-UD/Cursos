package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers= new ArrayList<>(Arrays.asList(
            new Customer(123, "Gerardo López", "gerardol", "contraseña123"),
            new Customer(456, "Alejandra Garcia", "alegarcia", "clave456"),
            new Customer(789, "Laura Sanchez", "lauras", "secreto789"),
            new Customer(234, "Carlos Martinez", "carlosm", "password234")
    ));

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customers);
    }

    // @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @GetMapping("/{username}")
    public ResponseEntity<?> getCustomer(@PathVariable String username){
        for(Customer c : customers){
            if(c.getUsername().equals(username)){
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with username: " + username);
    }

    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<Void> postCustomer(@RequestBody Customer customer){
        customers.add(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(customer.getUsername())
                .toUri();

        // Opcionalmente:

//        return ResponseEntity.status(HttpStatus.CREATED)
//                .header("Location", "http://localhost:8080/customers/" + customer.getUsername())
//                .build();

        return ResponseEntity.created(location).build();
    }

    // @RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<Void> putCustomer(@RequestBody Customer customer){
        for(Customer c: customers){
            if(c.getId() == customer.getId()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    // @RequestMapping(method = RequestMethod.PATCH)
    @PatchMapping
    public ResponseEntity<String> patchCustomer(@RequestBody Customer customer){
        for(Customer c: customers){
            if(c.getId() == customer.getId()){
                if(customer.getName() != null){
                    c.setName(customer.getName());
                }
                if(customer.getUsername() != null){
                    c.setUsername(customer.getUsername());
                }
                if(customer.getPassword() != null){
                    c.setPassword(customer.getPassword());
                }
                return ResponseEntity.ok("Customer modified successfully: " + customer.getId());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found: " + customer.getId());
    }

    // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id){
        for(Customer c: customers){
            if(c.getId() == id){
                customers.remove(c);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
