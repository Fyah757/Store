package org.fyahskuztum.ecommerce.store.service.impl;

import org.fyahskuztum.ecommerce.store.model.Item;
import org.fyahskuztum.ecommerce.store.model.Sale;
import org.fyahskuztum.ecommerce.store.repository.ItemRepository;
import org.fyahskuztum.ecommerce.store.repository.SaleRepository;
import org.fyahskuztum.ecommerce.store.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findByOrderByDateOfSaleDesc();
    }

    public Optional<Sale> getSaleById(Long saleId) {
        return saleRepository.findById(saleId);
    }

    public void addNewSale(Sale sale) {
        Item existingItem = itemRepository.findById(sale.getItem().getId()).get();

        // update item inventory based on sale quantity
        int newCount = existingItem.getInventory() - sale.getQuantity();
        existingItem.setInventory(newCount);
        itemRepository.save(existingItem);
        saleRepository.save(sale);
    }

    public void updateSale(Sale updatedSale) {
        Optional<Sale> sale = saleRepository.findById(updatedSale.getId());

        if (sale.isPresent()) {
            Sale existingSale = sale.get();

            // update item inventory depending on updated sale fields
            if (existingSale.getItem() != updatedSale.getItem()) {
                Item existingItem = itemRepository.findById(existingSale.getItem().getId()).get();
                existingItem.setInventory(existingItem.getInventory() + existingSale.getQuantity());
                Item updatedItem = itemRepository.findById(updatedSale.getItem().getId()).get();
                updatedItem.setInventory(updatedItem.getInventory() - updatedSale.getQuantity());
            }
            else if (existingSale.getQuantity() != updatedSale.getQuantity()) {
                Item existingItem = itemRepository.findById(existingSale.getItem().getId()).get();
                int difference = updatedSale.getQuantity() - existingSale.getQuantity();
                int newCount = existingItem.getInventory() - difference;
                existingItem.setInventory(newCount);
            }

            existingSale.setDateOfSale(updatedSale.getDateOfSale());
            existingSale.setItem(updatedSale.getItem());
            existingSale.setCustomer(updatedSale.getCustomer());
            existingSale.setQuantity(updatedSale.getQuantity());
            existingSale.setSalePrice(updatedSale.getSalePrice());
            existingSale.setPaid(updatedSale.isPaid());
            saleRepository.save(updatedSale);
        }
    }

    public void deleteSaleById(Long saleId) {
        // update item inventory before deleting a sale
        Sale sale = saleRepository.findById(saleId).get();
        Item item = itemRepository.findById(sale.getItem().getId()).get();
        item.setInventory(item.getInventory() + sale.getQuantity());
        saleRepository.deleteById(saleId);
    }

}
