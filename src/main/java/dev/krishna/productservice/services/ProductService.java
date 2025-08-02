package dev.krishna.productservice.services;

import java.util.*;
import dev.krishna.productservice.dtos.GenericProductDto;
import dev.krishna.productservice.exceptions.NotFoundException;

public interface ProductService {

    public GenericProductDto getProductById(Long id) throws NotFoundException;

    public GenericProductDto createProduct(GenericProductDto genericProductDto);

    public List<GenericProductDto> getAllProducts();

    public GenericProductDto deleteProduct(Long id);

    public GenericProductDto updateProductById(Long id);
}
