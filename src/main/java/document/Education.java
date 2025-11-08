package document;

import alkz.mscurriculum.model.EducationDto;
import alkz.mscurriculum.util.Utils;
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

  /**
   * Create a new Education from EducationDto.Request
   * @param request EducationDto.Request
   * @return Education
   */
  public static Education build(EducationDto.Request request) {
    return Education.builder()
        .id(Utils.generateObjectId())
        .level(request.level())
        .institute(request.institute())
        .degree(request.degree())
        .startDate(request.startDate())
        .endDate(request.endDate())
        .stillStudying(request.stillStudying())
        .build();
  }

  /**
   * Update education data
   * @param request EducationDto.Request
   */
  public void update(EducationDto.Request request) {
    this.level = request.level();
    this.institute = request.institute();
    this.degree = request.degree();
    this.startDate = request.startDate();
    this.endDate = request.endDate();
    this.stillStudying = request.stillStudying();
  }
}
