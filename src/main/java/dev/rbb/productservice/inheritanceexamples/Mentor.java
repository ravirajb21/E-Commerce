package dev.rbb.productservice.inheritanceexamples;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mentor extends User {
    private int numberOfSessions;
    private int getNumberOfMentees;
}