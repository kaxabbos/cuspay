package com.cuspay.model;

import com.cuspay.model.enums.ProductStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Products {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private float weight;
    private int price;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Fines fine;

    public Products(String name, float weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.status = ProductStatus.WAITING;
    }
}