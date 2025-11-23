package alkz.mscurriculum.document;

import alkz.mscurriculum.dto.LanguageDto;
import alkz.mscurriculum.util.Utils;
import alkz.mscurriculum.util.enums.ELanguageLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

  /**
   * Create a new language from LanguageDto.Request
   * @param request LanguageDto.Request
   * @return Language
   */
  public static Language build(LanguageDto.Request request) {
    return Language.builder()
        .id(Utils.generateObjectId())
        .name(request.name())
        .level(request.level())
        .build();
  }

  /**
   * Update language data
   * @param request LanguageDto.Request
   */
  public void update(LanguageDto.Request request) {
    this.name = request.name();
    this.level = request.level();
  }
}
