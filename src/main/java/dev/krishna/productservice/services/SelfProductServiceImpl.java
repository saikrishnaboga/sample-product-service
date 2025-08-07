package dev.krishna.productservice.services;

import dev.krishna.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {

    @Override
    public GenericProductDto getProductById(Long id){
        System.out.println("Calling from Self store product service");
        return null;

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public ArrayList<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id) {
        return null;
    }
}
