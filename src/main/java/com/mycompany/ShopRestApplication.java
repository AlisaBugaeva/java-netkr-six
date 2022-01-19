package com.mycompany;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title= "Shops API", version = "1.0", description = "Shops web service"))
public class ShopRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopRestApplication.class);
    }
}
