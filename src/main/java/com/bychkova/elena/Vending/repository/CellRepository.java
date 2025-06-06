package com.bychkova.elena.Vending.repository;

import com.bychkova.elena.Vending.entity.Cell;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepository extends CrudRepository <Cell, Long> {}
