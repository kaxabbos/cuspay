package com.cuspay.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum ProductStatus {
    WAITING("Ожидание"),
    PAID_FOR("Оплачено"),
    WITHDRAW("Изъят");

    private final String name;

}

