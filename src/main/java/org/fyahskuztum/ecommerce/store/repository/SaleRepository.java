package org.fyahskuztum.ecommerce.store.repository;


import org.fyahskuztum.ecommerce.store.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByItemId(Long itemId);

    List<Sale> findByCustomerId(Long custId);

    void deleteByItemId(Long itemId);

    void deleteByCustomerId(Long custId);

    List<Sale> findByOrderByDateOfSaleDesc();
}
