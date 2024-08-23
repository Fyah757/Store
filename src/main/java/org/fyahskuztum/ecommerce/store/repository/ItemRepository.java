package org.fyahskuztum.ecommerce.store.repository;

import org.fyahskuztum.ecommerce.store.model.Customer;
import org.fyahskuztum.ecommerce.store.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByName(String name);

    List<Item> findByInventoryLessThan(int inventory);

    List<Item> findByOrderByName();
}