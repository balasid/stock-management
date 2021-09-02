package com.altimetrik.interview.stockmanagement.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

    StockBootStrap bootStrap;

    @Autowired
    public void setBootStrap(StockBootStrap bootStrap) {
        this.bootStrap = bootStrap;
    }

    @Override
    public void run(String... args) throws Exception {
        bootStrap.processData();
    }
}
