package alkz.mscurriculum.document;

import alkz.mscurriculum.util.enums.ELanguageLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Document(collection = "Languages")
public class Language {

  @MongoId
  private String id;
  private String name;
  private ELanguageLevel level;
}

