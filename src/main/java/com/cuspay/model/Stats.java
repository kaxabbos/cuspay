package com.cuspay.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Stats {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private int fine;
    @OneToOne(fetch = FetchType.LAZY)
    private Users owner;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Incomes incomes;

    public Stats() {
        this.incomes = new Incomes(0);
        this.fine = 0;
    }
}