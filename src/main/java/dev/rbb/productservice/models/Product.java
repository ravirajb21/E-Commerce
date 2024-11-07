package dev.rbb.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    //P:C
    //1 -> 1
    //M <- 1
    //M <-> 1
    @ManyToOne
    private Category category;
    private String imageUrl;
}
