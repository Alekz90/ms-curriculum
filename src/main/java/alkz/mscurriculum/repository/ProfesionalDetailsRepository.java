package alkz.mscurriculum.repository;

import document.ProfessionalDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesionalDetailsRepository extends MongoRepository<ProfessionalDetail, String> {
  Optional<ProfessionalDetail> findByUserId(String userId);
}
