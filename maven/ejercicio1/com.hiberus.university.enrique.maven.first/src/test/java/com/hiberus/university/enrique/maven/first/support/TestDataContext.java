package com.hiberus.university.enrique.maven.first.support;

import com.hiberus.university.enrique.maven.first.model.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class TestDataContext {

    private static final List<InventoryItem> inventoryItemListInCart = new ArrayList<>();
    public static void addItem(InventoryItem item) { inventoryItemListInCart.add(item);}
    public  static List<InventoryItem> getInventoryItemListInCart(){
        return inventoryItemListInCart;
    }
}