package alkz.mscurriculum.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Profiles")
public class Profile {

  @MongoId
  private String id;
  private LocalDate birthDate;
  private String cellphone;
  private String image;
  private String fullName;
  private User user;
  private ProfessionalDetails profesionalDetails;
}

