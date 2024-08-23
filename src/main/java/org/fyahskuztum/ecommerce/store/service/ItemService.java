package org.fyahskuztum.ecommerce.store.service;

import org.fyahskuztum.ecommerce.store.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllItems();
    Optional<Item> getItemById(Long itemId);
    List<Item> getAllLowInventory(int quantity);
    Optional<Item> getItemByName(String itemName);
    void addNewItem(Item item);
    void updateItem(Item updatedItem);
    void deleteItemById(Long itemId);

}