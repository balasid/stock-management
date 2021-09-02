package com.altimetrik.interview.stockmanagement.service;

import com.altimetrik.interview.stockmanagement.exception.ResourceNotFoundException;
import com.altimetrik.interview.stockmanagement.model.Stock;
import com.altimetrik.interview.stockmanagement.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    StockRepository stockRepository;

    @Autowired
    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Stock getStock(Long stockNumber) {

        return stockRepository.findById(stockNumber).orElseThrow(() -> new ResourceNotFoundException("No Stock available with Id " + stockNumber));
    }

    @Override
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Stock stock) {
        Long stockNumber = stock.getStockNumber();
        if (stockRepository.findById(stockNumber).isPresent()) {
            return stockRepository.save(stock);
        } else {
            throw new ResourceNotFoundException("No Stock available with Id " + stockNumber);
        }
    }

    @Override
    public void delete(String stockNumber) {
        //todo Throw Not supported Exception
    }
}
