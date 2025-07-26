package dev.krishna.productservice.services;

import dev.krishna.productservice.dtos.FakeStoreProductDto;
import dev.krishna.productservice.dtos.GenericProductDto;
import dev.krishna.productservice.exceptions.NotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{



    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public GenericProductDto convertfakeStoreDtoToGenericStoreDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());

        return genericProductDto;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto>  response = restTemplate.getForEntity(productUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id: " + id + " not found");
        }

        GenericProductDto genericProductDto = convertfakeStoreDtoToGenericStoreDto(fakeStoreProductDto);

        return  genericProductDto;
    }




    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.postForEntity(
                productRequestUrl, product, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto genericProductDto = convertfakeStoreDtoToGenericStoreDto(fakeStoreProductDto);

        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(productRequestUrl, FakeStoreProductDto[].class);

//        ParameterizedTypeReference<List<FakeStoreProductDto>> other way to achieve  it
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        List<GenericProductDto> listOfProducts = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){

            GenericProductDto genericProductDto = convertfakeStoreDtoToGenericStoreDto(fakeStoreProductDto);
            listOfProducts.add(genericProductDto);
        }


        return listOfProducts;


    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =   restTemplate.exchange(productUrl, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto genericProductDto = convertfakeStoreDtoToGenericStoreDto(fakeStoreProductDto);

        return genericProductDto;
    }
}
