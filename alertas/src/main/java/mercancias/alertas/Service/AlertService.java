package mercancias.alertas.Service;

import jakarta.transaction.Transactional;
import mercancias.alertas.Repository.AlertRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AlertService {

    private final AlertRepository alertRepo;

}
