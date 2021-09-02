package com.altimetrik.interview.stockmanagement.controller;

import com.altimetrik.interview.stockmanagement.controller.exception.ErrorMessage;
import com.altimetrik.interview.stockmanagement.model.Stock;
import com.altimetrik.interview.stockmanagement.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/stock")
@RestController
public class StockController {
    private StockService stockService;

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @Operation(summary = "Retrieve all the Stock Details.",
            description = "Retrieve all the Stock Details.",
            responses = @ApiResponse(responseCode = "200", description = "List of Stocks",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Stock.class))),
                            @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Stock.class)))}))
    @GetMapping()
    public ResponseEntity<List<Stock>> getAllStocks() {
        return new ResponseEntity<>(stockService.getAllStocks(), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve the specific Stock Details.",
            description = "Detail description of the corresponding Stock.",
            responses = {@ApiResponse(responseCode = "200", description = "Stocks Details",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Stock.class)),
                            @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                    schema = @Schema(implementation = Stock.class))}),
                    @ApiResponse(responseCode = "404", description = "Stock Not Found",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)),
                                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                            schema = @Schema(implementation = ErrorMessage.class))})})
    @GetMapping(value = "/{id}")
    public ResponseEntity<Stock> getStockByNumber(@Parameter(description = "Id of the Stock.", required = true) @PathVariable Long id) {
        return new ResponseEntity<>(stockService.getStock(id), HttpStatus.OK);
    }

    @Operation(summary = "Add a new Stock.",
            description = "Add a new Stock to the Stock Repository.",
            responses = @ApiResponse(responseCode = "201", description = "Stock Created",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Stock.class)),
                            @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                    schema = @Schema(implementation = Stock.class))
                    }))
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Stock> createStock(@Parameter(description = "Stock Details", required = true, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Stock.class)),
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = Stock.class))}) @RequestBody Stock stock) {
        return new ResponseEntity<>(stockService.createStock(stock), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a new Stock.",
            description = "Update a existing Stock in the Stock Repository.",
            responses = {@ApiResponse(responseCode = "202", description = "Stock Updated",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Stock.class)),
                            @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                    schema = @Schema(implementation = Stock.class))
                    }), @ApiResponse(responseCode = "404", description = "Stock Not Found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)),
                            @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class))
                    })})
    @PutMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Stock> updateStock(@Parameter(description = "Stock Details", required = true, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Stock.class)),
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = Stock.class))}) @RequestBody Stock stock) {
        return new ResponseEntity<>(stockService.updateStock(stock), HttpStatus.ACCEPTED);
    }

}
