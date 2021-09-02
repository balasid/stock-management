package com.altimetrik.interview.stockmanagement.service;


import com.altimetrik.interview.stockmanagement.model.Stock;

import java.util.List;

public interface StockService {

    List<Stock> getAllStocks();

    Stock getStock(Long stockNumber);

    Stock createStock(Stock stock);

    Stock updateStock(Stock stock);

    void delete(String stockNumber);

}
