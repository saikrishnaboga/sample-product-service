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
        List<GenericProductDto> listOfProducts = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreProductDto>> response =
                restTemplate.exchange(productRequestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<FakeStoreProductDto>>(){});
        List<FakeStoreProductDto> products =  response.getBody();
        for(int i=0; i< products.size(); i++){

            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setId(products.get(i).getId());
            genericProductDto.setCategory(products.get(i).getCategory());
            genericProductDto.setTitle(products.get(i).getTitle());
            genericProductDto.setPrice(products.get(i).getPrice());
            genericProductDto.setDescription(products.get(i).getDescription());
            genericProductDto.setImage(products.get(i).getImage());
            listOfProducts.add(genericProductDto);
        }


        return listOfProducts;


    }
}
