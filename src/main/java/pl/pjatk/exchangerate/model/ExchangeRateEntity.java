package pl.pjatk.exchangerate.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entity representing an average exchange rate for a currency over a period")
public class ExchangeRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the record", example = "1")
    private Long id;

    @Schema(description = "Currency code (ISO 4217)", example = "PLN")
    private String currency;

    @Schema(description = "Start date of the period (inclusive)", example = "2025-06-01")
    private LocalDate startDate;

    @Schema(description = "End date of the period (inclusive)", example = "2025-06-01")
    private LocalDate endDate;

    @Schema(description = "Average exchange rate calculated for the period", example = "2.13742069")
    private double averageRate;

    @Schema(description = "Timestamp when the data was queried and stored", example = "2025-06-21T13:45:00")
    private LocalDateTime queryTimestamp;

    public ExchangeRateEntity() {}

    public ExchangeRateEntity(Long id, String currency, LocalDate startDate, LocalDate endDate, double averageRate, LocalDateTime queryTimestamp) {
        this.id = id;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.averageRate = averageRate;
        this.queryTimestamp = queryTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public LocalDateTime getQueryTimestamp() {
        return queryTimestamp;
    }

    public void setQueryTimestamp(LocalDateTime queryTimestamp) {
        this.queryTimestamp = queryTimestamp;
    }
}
