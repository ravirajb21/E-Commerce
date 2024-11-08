package dev.rbb.productservice.inheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ms_TA")
public class TA extends User {
    private double averageRating;
}
