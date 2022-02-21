package com.company.skyproeducationspring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Employee {
    private final String firstName;
    private final String lastName;

    @Override
    public String toString() {
        return "Employee {" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
