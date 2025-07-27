package dev.krishna.productservice.services;

import dev.krishna.productservice.dtos.FakeStoreProductDto;
import dev.krishna.productservice.dtos.GenericProductDto;
import dev.krishna.productservice.exceptions.NotFoundException;
import dev.krishna.productservice.thirdpartyclients.fakestore.FakeStoreProductCilent;
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

    private FakeStoreProductCilent fakeStoreProductCilent;

    @Autowired
    public FakeStoreProductService(FakeStoreProductCilent fakeStoreProductCilent){
        this.fakeStoreProductCilent = fakeStoreProductCilent;
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
        return convertfakeStoreDtoToGenericStoreDto(
                fakeStoreProductCilent.getProductById(id)
        );
    }




    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertfakeStoreDtoToGenericStoreDto(
                fakeStoreProductCilent.createProduct(product)
        );
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

//        ParameterizedTypeReference<List<FakeStoreProductDto>> other way to achieve  it
        List<FakeStoreProductDto> fakeStoreProductDtos =
                fakeStoreProductCilent.getAllProducts();

        List<GenericProductDto> listOfProducts = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){

            GenericProductDto genericProductDto = convertfakeStoreDtoToGenericStoreDto(fakeStoreProductDto);
            listOfProducts.add(genericProductDto);
        }


        return listOfProducts;


    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return convertfakeStoreDtoToGenericStoreDto(
                fakeStoreProductCilent.deleteProduct(id)
        );
    }
}
