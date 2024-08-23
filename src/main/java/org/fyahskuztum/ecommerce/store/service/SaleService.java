package org.fyahskuztum.ecommerce.store.service;

import org.fyahskuztum.ecommerce.store.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {

    List<Sale> getAllSales();
    Optional<Sale> getSaleById(Long saleId);
    void addNewSale(Sale sale);
    void updateSale(Sale updatedSale);
    void deleteSaleById(Long saleId);

}