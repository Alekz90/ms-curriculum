package alkz.mscurriculum.document;

import alkz.mscurriculum.model.ProfessionalDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "ProfesionalDetails")
public class ProfessionalDetail {

  @MongoId
  private String id;
  private String userId;
  private String position;
  private String summary;
  private List<ProfessionalExperience> profesionalExperiences;
  private List<Language> languages;
  private List<AbilityGroup> abilityGroups;
  private List<Education> educations;
  private List<Certification> certifications;
  private List<Link> links;
  private Address address;

  /**
   * Build ProfessionalDetail from ProfessionalDetailDto.Request
   * @param request ProfessionalDetailDto.Request
   * @return ProfessionalDetail
   */
  public static ProfessionalDetail build(ProfessionalDetailDto.Request request) {
    return ProfessionalDetail.builder()
        .userId(request.idUser())
        .position(request.position())
        .summary(request.summary())
        .profesionalExperiences(new ArrayList<>())
        .languages(new ArrayList<>())
        .abilityGroups(new ArrayList<>())
        .educations(new ArrayList<>())
        .certifications(new ArrayList<>())
        .links(new ArrayList<>())
        .build();
  }

  /**
   * Update ProfessionalDetail from ProfessionalDetailDto.Request
   * @param detail ProfessionalDetail
   * @param request ProfessionalDetailDto.Request
   * @return ProfessionalDetail
   */
  public static ProfessionalDetail update(ProfessionalDetail detail, ProfessionalDetailDto.Request request) {
    detail.setPosition(request.position());
    detail.setSummary(request.summary());
    return detail;
  }
}

