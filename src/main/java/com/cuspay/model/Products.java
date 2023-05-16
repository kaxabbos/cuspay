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

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Fines fine;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Names name;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Weights weight;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Prices price;

    public Products(String name, float weight, int price) {
        this.name = new Names(name);
        this.weight = new Weights(weight);
        this.price = new Prices(price);
        this.status = ProductStatus.WAITING;
    }
}