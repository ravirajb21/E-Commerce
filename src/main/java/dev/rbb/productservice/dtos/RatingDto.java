package dev.rbb.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {
    private double rate;
    private double count;
}
