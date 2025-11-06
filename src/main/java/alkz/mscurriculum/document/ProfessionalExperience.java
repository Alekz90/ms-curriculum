package alkz.mscurriculum.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Document(collection = "ProfessionalExperiences")
public class ProfessionalExperience {

  @MongoId
  private String id;
  private String company;
  private String position;
  private LocalDate startDate;
  private LocalDate endDate;
  private String stillWorking;
  private String activities;
}

