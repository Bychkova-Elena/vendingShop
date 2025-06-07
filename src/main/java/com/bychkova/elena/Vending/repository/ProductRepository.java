package com.bychkova.elena.Vending.repository;

import com.bychkova.elena.Vending.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{}
