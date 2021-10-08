package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private Map<String, Product> productMap = new HashMap<>();

    public VendingMachine(Map<String, Product> productMap) {
        this.productMap = productMap;
    }

    public Map<String, Product> getProductMap() {
        return productMap;
    }


    //feed money
    //dispense product

}
