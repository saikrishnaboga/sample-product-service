package dev.krishna.productservice.controllers;
import java.util.*;

import dev.krishna.productservice.dtos.GenericProductDto;
import dev.krishna.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

//    @Autowired
//    @Qualifier("selfProductServiceImpl")
    private ProductService productService;
//
    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

//    @Autowired
//    public void setProductService(){
//
//    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) {
        return  productService.getProductById(id);
    }

    public void updateProductById() {

    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("id")
    public GenericProductDto deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
