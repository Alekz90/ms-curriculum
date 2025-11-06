package alkz.mscurriculum.service;

import alkz.mscurriculum.repository.VerificationsRepository;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfesionalDetailsService implements IProfessionalDetailsService {

  private final VerificationsRepository repository;

}
