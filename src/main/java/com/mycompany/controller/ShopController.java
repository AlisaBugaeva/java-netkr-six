package com.mycompany.controller;

import com.mycompany.exception.ResourseNotFoundException;
import com.mycompany.model.Shop;
import com.mycompany.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class ShopController {
    @Autowired
    ShopRepository repository;

    @DeleteMapping("/shops/{id}")
    public Map<String, Boolean> deleteShop(@PathVariable(value = "id") Integer id) throws ResourseNotFoundException {

        Shop shop = repository.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("Shop is not found by id" + id)
        );

        repository.delete(shop);

        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", true);

        return response;
    }

    @PostMapping("/shops")
    public Shop createShop(@RequestBody Shop shop){
        return repository.save(shop);
    }

    @GetMapping("/shops")
    public List<Shop> getAllShops(){
        return repository.findAll();
    }

    @GetMapping("/shops/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable(value = "id") Integer id)
            throws ResourseNotFoundException {

        Shop shop = repository.findById(id).orElseThrow(
                ()-> new ResourseNotFoundException("Shop is not found by id" + id)
        );

        return ResponseEntity.ok().body(shop);
    }

    @PutMapping("/shops/{id}")
    Shop replaceShop(@RequestBody Shop newShop, @PathVariable Integer id)
            throws ResourseNotFoundException {
        return repository.findById(id)
                .map(shop -> {
                    shop.setShopName (newShop.getShopName());
                    shop.setDistrict (newShop.getDistrict());
                    shop.setCommission (newShop.getCommission());
                    return repository.save(shop);
                })
                .orElseThrow(
                        ()-> new ResourseNotFoundException("Shop is not found by id" + id)
                );
    }

}
