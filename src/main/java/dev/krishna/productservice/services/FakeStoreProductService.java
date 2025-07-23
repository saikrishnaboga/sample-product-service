package dev.krishna.productservice.services;

import dev.krishna.productservice.dtos.FakeStoreProductDto;
import dev.krishna.productservice.dtos.GenericProductDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private String productUrl = "https://fakestoreapi.com/products/{id}";
    private String productRequestUrl = "https://fakestoreapi.com/products/";

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto>  response = restTemplate.getForEntity(productUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setImage(fakeStoreProductDto.getImage());


        return  genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.postForEntity(
                productRequestUrl, product, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());

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

            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setId(fakeStoreProductDto.getId());
            genericProductDto.setCategory(fakeStoreProductDto.getCategory());
            genericProductDto.setTitle(fakeStoreProductDto.getTitle());
            genericProductDto.setPrice(fakeStoreProductDto.getPrice());
            genericProductDto.setDescription(fakeStoreProductDto.getDescription());
            genericProductDto.setImage(fakeStoreProductDto.getImage());

            listOfProducts.add(genericProductDto);
        }


        return listOfProducts;


    }
}
