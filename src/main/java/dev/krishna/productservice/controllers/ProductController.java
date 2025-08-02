package dev.krishna.productservice.controllers;
import java.util.*;

import dev.krishna.productservice.dtos.ExceptionDto;
import dev.krishna.productservice.dtos.GenericProductDto;
import dev.krishna.productservice.exceptions.NotFoundException;
import dev.krishna.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

//    @Autowired
//    @Qualifier("selfProductServiceImpl")
    private ProductService productService;
//
    @Autowired
    public ProductController(ProductService productService){
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
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return  productService.getProductById(id);
    }

    public GenericProductDto updateProductById() {
        return productService.updateProductById();
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
//        return productService.deleteProduct(id);
    }



//    this is specific to this controller
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handlerNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()), HttpStatus.NOT_FOUND);
//    }
}
