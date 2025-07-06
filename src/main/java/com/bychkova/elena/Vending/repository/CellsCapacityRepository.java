package com.bychkova.elena.Vending.repository;

import com.bychkova.elena.Vending.entity.CellsCapacity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellsCapacityRepository extends CrudRepository<CellsCapacity, Long> {}
