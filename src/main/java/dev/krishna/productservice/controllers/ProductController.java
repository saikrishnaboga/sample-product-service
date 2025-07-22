package dev.krishna.productservice.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping()
    public void getAllProducts(){

    }

    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "here is the product with id: " + id;
    }

    public void updateProductById() {

    }

    @PostMapping()
    public void createProduct() {

    }


}
