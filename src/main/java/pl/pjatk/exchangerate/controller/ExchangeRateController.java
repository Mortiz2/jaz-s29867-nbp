package pl.pjatk.exchangerate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.exchangerate.model.ExchangeRateEntity;
import pl.pjatk.exchangerate.service.ExchangeRateService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateController {

    private final ExchangeRateService service;

    public ExchangeRateController(ExchangeRateService service) {
        this.service = service;
    }

    @Operation(summary = "Get average exchange rate for currency in given period")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping
    public ResponseEntity<ExchangeRateEntity> getAverageExchangeRate(
            @Parameter(description = "Currency code", required = true)
            @RequestParam String currency,
            @Parameter(description = "Start date yyyy-MM-dd", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "End date yyyy-MM-dd", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        double rate = service.getAverageRate(currency.toUpperCase(), startDate, endDate);

        ExchangeRateEntity entity = new ExchangeRateEntity();
        entity.setCurrency(currency.toUpperCase());
        entity.setStartDate(startDate);
        entity.setEndDate(endDate);
        entity.setAverageRate(rate);
        entity.setQueryTimestamp(LocalDateTime.now());

        return ResponseEntity.ok(entity);
    }
}

