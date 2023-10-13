package com.multipolar.bootcamp.product.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document
public class Product {

    @Id
    private String id;
    private String productName;
    private ProductType productType;
    private double interestRate;
    private double minimumBalance;
    private double maximumLoanAmount;
    private String termsAndConditions;
    private LocalDateTime dateOfCreation = LocalDateTime.now();
}
