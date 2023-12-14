package com.example.moneyapi.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
    private String address;
    private String contact;
    private String complement;
    private String cep;
    private String city;
    private String state;
}
