package dev.krishna.productservice.thirdpartyclients.fakestore;

import dev.krishna.productservice.dtos.FakeStoreProductDto;
import dev.krishna.productservice.dtos.GenericProductDto;
import dev.krishna.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class FakeStoreProductCilent {

    @Value("fakestore.api.baseurl")
    private String fakeStoreApiBaseUrl;

    @Value("fakestore.api.product")
    private String fakeStoreProductPath;

    private final String productPath = "/products";

    private String productUrl = fakeStoreApiBaseUrl + productPath + "/{id}";

    private String productBaseUrl = fakeStoreApiBaseUrl + fakeStoreProductPath;

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductCilent(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        System.out.println(fakeStoreApiBaseUrl);
        System.out.println(productUrl);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(productUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id: " + id + " not found");
        }

        return  fakeStoreProductDto;
    }


    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.postForEntity(
                productRequestUrl, product, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(productRequestUrl, FakeStoreProductDto[].class);

//        ParameterizedTypeReference<List<FakeStoreProductDto>> other way to achieve  it
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();

        return Arrays.asList(fakeStoreProductDtos);

    }

    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =   restTemplate.exchange(productUrl, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }
}
