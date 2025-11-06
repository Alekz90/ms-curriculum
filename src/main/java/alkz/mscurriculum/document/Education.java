package alkz.mscurriculum.document;

import alkz.mscurriculum.util.enums.EEducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Document(collection = "Educations")
public class Education {

  @MongoId
  private String id;
  private EEducationLevel level;
  private String institute;
  private String degree;
  private Year startDate;
  private Year endDate;
  private Boolean stillStudying;
}

