package alkz.mscurriculum.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Document(collection = "Certifications")
public class Certification {

  @MongoId
  private String id;
  private String name;
  private String description;
}

