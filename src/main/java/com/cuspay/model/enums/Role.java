package com.cuspay.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    ADMIN("Директор"),
    MANAGER("Сотрудник"),
    CLIENT("Физлицо");

    private final String name;

    @Override
    public String getAuthority() {
        return name();
    }
}

