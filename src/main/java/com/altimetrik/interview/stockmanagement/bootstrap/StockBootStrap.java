package com.altimetrik.interview.stockmanagement.bootstrap;

import com.altimetrik.interview.stockmanagement.model.Stock;
import com.altimetrik.interview.stockmanagement.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StockBootStrap {
    private StockRepository stockRepository;

    @Autowired
    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void processData() {
        Stock stock = new Stock("Reliance Industries", 225.65, new Date(), 30);
        stockRepository.save(stock);
        stock = new Stock("Tata Industries", 250.65, new Date(), 50);
        stockRepository.save(stock);
        stock = new Stock("Hindustan Unilever Ltd", 250.65, new Date(), 100);
        stockRepository.save(stock);
    }

}
