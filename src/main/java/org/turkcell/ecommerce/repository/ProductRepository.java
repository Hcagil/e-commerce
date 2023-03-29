package org.turkcell.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.turkcell.ecommerce.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
