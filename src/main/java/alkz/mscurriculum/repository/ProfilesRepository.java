package alkz.mscurriculum.repository;

import alkz.mscurriculum.document.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilesRepository extends MongoRepository<Profile, String> {
  Optional<Profile> findByUserId(String userId);
  Optional<Profile> findByCodePhoneAndCellphone(String codePhone, String cellphone);
}
