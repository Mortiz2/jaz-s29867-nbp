package pl.pjatk.exchangerate.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pl.pjatk.exchangerate.dto.NbpResponse;
import pl.pjatk.exchangerate.dto.NbpRate;
import pl.pjatk.exchangerate.model.ExchangeRateEntity;
import pl.pjatk.exchangerate.repo.ExchangeRateRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExchangeRateService {

    private final RestTemplate restTemplate;
    private final ExchangeRateRepository repository;

    public ExchangeRateService(RestTemplateBuilder builder, ExchangeRateRepository repository) {
        this.restTemplate = builder.build();
        this.repository = repository;
    }

    public double getAverageRate(String currency, LocalDate startDate, LocalDate endDate) {
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/A/%s/%s/%s?format=json",
                currency, startDate, endDate);

        try {
            NbpResponse response = restTemplate.getForObject(url, NbpResponse.class);
            List<NbpRate> rates = response.getRates();

            double avg = rates.stream()
                    .mapToDouble(NbpRate::getMid)
                    .average()
                    .orElseThrow();

            repository.save(new ExchangeRateEntity(
                    null, currency, startDate, endDate, avg, LocalDateTime.now()
            ));

            return avg;

        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode(), "NBP API error: " + e.getResponseBodyAsString());
        }
    }
}
