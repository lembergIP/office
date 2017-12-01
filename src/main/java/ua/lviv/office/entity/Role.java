package ua.lviv.office.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    EMPLOYER("EMPLOYER"),
    MANAGER("MANAGER"),
    OFFICE_MANAGER("OFFICE_MANAGER"),
    ADMINISTRATOR("ADMINISTRATOR"),
    ACCOUNTANT("ACCOUNTANT");
    String name;

    Role(final String name){
        this.name=name;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
