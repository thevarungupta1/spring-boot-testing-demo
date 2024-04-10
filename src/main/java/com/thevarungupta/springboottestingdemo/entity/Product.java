package com.thevarungupta.springboottestingdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private String lastUpdated;
    private String price;
    private String summary;
}
