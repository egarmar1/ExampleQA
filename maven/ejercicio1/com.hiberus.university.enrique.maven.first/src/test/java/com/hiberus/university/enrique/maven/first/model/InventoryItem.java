package com.hiberus.university.enrique.maven.first.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class InventoryItem {
    private String name;
    private String description;
    private String price;
}
