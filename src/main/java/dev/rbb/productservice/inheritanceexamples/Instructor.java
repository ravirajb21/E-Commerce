package dev.rbb.productservice.inheritanceexamples;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instructor extends User {
    private boolean isSmart;
}
