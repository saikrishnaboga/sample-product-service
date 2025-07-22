package dev.krishna.productservice.services;

import java.util.*;
import dev.krishna.productservice.dtos.GenericProductDto;

public interface ProductService {

    public GenericProductDto getProductById(Long id);

    public GenericProductDto createProduct(GenericProductDto genericProductDto);

    public List<GenericProductDto> getAllProducts();
}
