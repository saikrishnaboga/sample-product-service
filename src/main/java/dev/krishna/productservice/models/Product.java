package dev.krishna.productservice.models;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;

    @ManyToOne
    private String category;
    private double price;

    public static void main(String args[]){
        UUID id = new UUID.randomUUID();
        System.out.println(id);
    }
}
