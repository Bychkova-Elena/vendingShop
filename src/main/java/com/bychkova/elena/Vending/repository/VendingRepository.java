package com.bychkova.elena.Vending.repository;

import com.bychkova.elena.Vending.entity.Vending;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface VendingRepository extends CrudRepository<Vending, Long> {}
