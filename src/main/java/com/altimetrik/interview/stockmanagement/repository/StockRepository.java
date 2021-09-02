package com.altimetrik.interview.stockmanagement.repository;

import com.altimetrik.interview.stockmanagement.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
