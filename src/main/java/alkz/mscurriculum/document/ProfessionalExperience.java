package alkz.mscurriculum.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "ProfessionalExperiences")
public class ProfessionalExperience {
  @Id
  private String id;
  private String position;
  private LocalDate startDate;
  private LocalDate endDate;
  private String stillWorking;
  private String activities;
}

