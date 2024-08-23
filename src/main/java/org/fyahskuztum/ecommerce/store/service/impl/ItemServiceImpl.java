package org.fyahskuztum.ecommerce.store.service.impl;

import jakarta.transaction.Transactional;
import org.fyahskuztum.ecommerce.store.model.Item;
import org.fyahskuztum.ecommerce.store.repository.ItemRepository;
import org.fyahskuztum.ecommerce.store.repository.SaleRepository;
import org.fyahskuztum.ecommerce.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SaleRepository saleRepository;

    public List<Item> getAllItems() {
        return itemRepository.findByOrderByName();
    }

    public Optional<Item> getItemById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public List<Item> getAllLowInventory(int quantity){
        return itemRepository.findByInventoryLessThan(quantity);
    }

    public Optional<Item> getItemByName(String itemName){
        return itemRepository.findByName(itemName);
    }

    public void addNewItem(Item item) {
        itemRepository.save(item);
    }

    public void updateItem(Item updatedItem) {
        Optional<Item> item = itemRepository.findById(updatedItem.getId());

        if(item.isPresent()) {
            Item existingItem = item.get();
            existingItem.setName(updatedItem.getName());
            existingItem.setDescription(updatedItem.getDescription());
            existingItem.setCost(updatedItem.getCost());
            existingItem.setInventory(updatedItem.getInventory());
            itemRepository.save(existingItem);
        }
    }

    @Transactional
    public void deleteItemById(Long itemId) {
        saleRepository.deleteByItemId(itemId);
        itemRepository.deleteById(itemId);
    }

}
