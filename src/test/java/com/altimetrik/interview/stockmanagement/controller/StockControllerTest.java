package com.altimetrik.interview.stockmanagement.controller;

import com.altimetrik.interview.stockmanagement.model.Stock;
import com.altimetrik.interview.stockmanagement.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class StockControllerTest {

    public static final String RELIANCE_INDUSTRIES = "Reliance Industries";

    MockMvc mockMvc;
    @Mock
    private StockService stockService;
    @InjectMocks
    private StockController stockController;

    @Before
    public void setupEnv() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();

    }

    @Test
    public void testGetAll() throws Exception {
        ArrayList<Stock> stocks = new ArrayList<>();
        String relianceIndustries = RELIANCE_INDUSTRIES;
        String relianceIndustries2 = RELIANCE_INDUSTRIES + "12";
        stocks.add(new Stock(relianceIndustries, 225.65, new Date(), 30));
        stocks.add(new Stock(relianceIndustries2, 250, new Date(), 35));

        Mockito.when(stockService.getAllStocks()).thenReturn(stocks);

        mockMvc.perform(MockMvcRequestBuilders.get("/stock").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].stockName", is(relianceIndustries)))
                .andExpect(jsonPath("$[1].stockName", is(relianceIndustries2)));
    }

    @Test
    public void testGetSpecific() throws Exception {
        Stock mockResponse = new Stock(RELIANCE_INDUSTRIES, 225.65, new Date(), 30);
        Mockito.when(stockService.getStock(1L)).thenReturn(mockResponse);
        mockMvc.perform(MockMvcRequestBuilders.get("/stock/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stockName", is(RELIANCE_INDUSTRIES)));
    }

    @Test
    public void testCreateSpecific() throws Exception {
        Stock mockResponse = new Stock(1L, RELIANCE_INDUSTRIES, 225.65, new Date(), 30);
        Mockito.when(stockService.createStock(any())).thenReturn(mockResponse);
        String jsonRequestBody = getObjectAsJson(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/stock").accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).content(jsonRequestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.stockName", is(RELIANCE_INDUSTRIES)));
    }

    private String getObjectAsJson(Stock mockResponse) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(mockResponse);
    }

    @Test
    public void testUpdateSpecific() throws Exception {
        Stock mockResponse = new Stock(RELIANCE_INDUSTRIES, 225.65, new Date(), 30);
        Mockito.when(stockService.updateStock(any())).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/stock").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(getObjectAsJson(mockResponse)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.stockName", is(RELIANCE_INDUSTRIES)));
    }

}
