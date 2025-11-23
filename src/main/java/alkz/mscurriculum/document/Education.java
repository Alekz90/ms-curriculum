package alkz.mscurriculum.document;

import alkz.mscurriculum.dto.EducationDto;
import alkz.mscurriculum.util.Utils;
import alkz.mscurriculum.util.enums.EEducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
  private Integer startDate;
  private Integer endDate;
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
        .startDate(request.startYear())
        .endDate(request.endYear())
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
    this.startDate = request.startYear();
    this.endDate = request.endYear();
    this.stillStudying = request.stillStudying();
  }
}
