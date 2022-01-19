package com.mycompany.controller;

import com.mycompany.exception.ResourseNotFoundException;
import com.mycompany.model.Buying;
import com.mycompany.repository.BuyingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class BuyingController {
    @Autowired
    BuyingRepository repository;

    @DeleteMapping("/buying/{id}")
    public Map<String, Boolean> deleteBuying(@PathVariable(value = "id") Integer id) throws ResourseNotFoundException {

        Buying buying = repository.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("Buying is not found by id" + id)
        );

        repository.delete(buying);

        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", true);

        return response;
    }

    @PostMapping("/buying")
    public Buying createBuying(@RequestBody Buying buying){
        return repository.save(buying);
    }

    @GetMapping("/buying")
    public List<Buying> getAllBuying(){
        return repository.findAll();
    }

    @GetMapping("/buying/{id}")
    public ResponseEntity<Buying> getBuyingById(@PathVariable(value = "id") Integer id)
            throws ResourseNotFoundException {

        Buying buying = repository.findById(id).orElseThrow(
                ()-> new ResourseNotFoundException("Buying is not found by id" + id)
        );

        return ResponseEntity.ok().body(buying);
    }

    @PutMapping("/buying/{id}")
    Buying replaceBook(@RequestBody Buying newBuying, @PathVariable Integer id)
            throws ResourseNotFoundException {

        return repository.findById(id)
                .map(buying -> {
                    buying.setBuyingDate (newBuying.getBuyingDate());
                    buying.setShop (newBuying.getShop());
                    buying.setCustomer (newBuying.getCustomer());
                    buying.setBook (newBuying.getBook());
                    buying.setKolvo (newBuying.getKolvo());
                    buying.setBuyingSum (newBuying.getBuyingSum());
                    return repository.save(buying);
                })
                .orElseThrow(
                        ()-> new ResourseNotFoundException("Buying is not found by id" + id)
                );
    }
}
