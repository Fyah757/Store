package org.fyahskuztum.ecommerce.store.repository;

import org.fyahskuztum.ecommerce.store.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByOrderByFirstName();

    Optional<Customer> findByEmail(String email);
}
