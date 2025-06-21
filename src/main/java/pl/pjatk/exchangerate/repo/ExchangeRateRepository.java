package pl.pjatk.exchangerate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.exchangerate.model.ExchangeRateEntity;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {
}

