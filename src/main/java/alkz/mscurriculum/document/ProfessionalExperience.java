package alkz.mscurriculum.document;

import alkz.mscurriculum.dto.ExperienceDto;
import alkz.mscurriculum.util.Utils;
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
public class ProfessionalExperience {

  @MongoId
  private String id;
  private String company;
  private String position;
  private LocalDate startDate;
  private LocalDate endDate;
  private Boolean stillWorking;
  private String activities;
  private Address location;

  /**
   * Build a ProfessionalExperience document from an ExperienceDto.Request
   * @param request ExperienceDto.Request
   * @return ProfessionalExperience
   */
  public static ProfessionalExperience build(ExperienceDto.Request request) {
    return ProfessionalExperience.builder()
        .id(Utils.generateObjectId())
        .company(request.company())
        .position(request.position())
        .startDate(request.startDate())
        .endDate(request.endDate())
        .stillWorking(request.stillWorking())
        .activities(request.activities())
        .location(Address.build(request.location()))
        .build();
  }

  /**
   * Update ProfessionalExperience data
   * @param request ExperienceDto.Request
   */
  public void update(ExperienceDto.Request request) {
    this.company = request.company();
    this.position = request.position();
    this.startDate = request.startDate();
    this.endDate = request.endDate();
    this.stillWorking = request.stillWorking();
    this.activities = request.activities();
    this.location.update(request.location());
  }
}

