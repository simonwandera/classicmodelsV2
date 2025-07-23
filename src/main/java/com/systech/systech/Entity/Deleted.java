package com.systech.systech.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Deleted {

    DELETED("Deleted"),

    AVAILABLE("Available");

    private final String name;

    Deleted(String value) {
        this.name = value;
    }
}
