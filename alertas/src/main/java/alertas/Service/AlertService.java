package alertas.Service;

import jakarta.transaction.Transactional;
import alertas.Repository.AlertRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AlertService {

    private final AlertRepository alertRepo;

    public AlertService(AlertRepository alertRepo) {
        this.alertRepo = alertRepo;
    }

}
