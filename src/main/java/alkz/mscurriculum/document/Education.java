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
@Document(collection = "Educations")
public class Education {

  @MongoId
  private String id;
  private String level;
  private String institute;
  private String degree;
  private LocalDate startDate;
  private LocalDate endDate;
  private Boolean stillStudying;
}

