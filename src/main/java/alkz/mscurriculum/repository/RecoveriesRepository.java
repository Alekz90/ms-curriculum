package alkz.mscurriculum.repository;

import alkz.mscurriculum.document.Recovery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RecoveriesRepository extends MongoRepository<Recovery, String> {
  Optional<Recovery> findByEmailAndUsedFalseAndExpirationDateAfter(String email, LocalDateTime currentDateTime);
}
