package alkz.mscurriculum.document;

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
  private Summary summary;
  private List<ProfessionalExperience> experiences;
  private List<Language> languages;
  private List<AbilityGroup> abilityGroups;
  private List<Education> educations;
  private List<Certification> certifications;
  private List<Link> links;

  /**
   * Create an empty professional detail for a user
   * @param userId User id
   * @return ProfessionalDetail
   */
  public static ProfessionalDetail emptyDetail(String userId) {
    return ProfessionalDetail.builder()
        .userId(userId)
        .experiences(new ArrayList<>())
        .languages(new ArrayList<>())
        .abilityGroups(new ArrayList<>())
        .educations(new ArrayList<>())
        .certifications(new ArrayList<>())
        .links(new ArrayList<>())
        .build();
  }

  /**
   * Update professional detail data
   * @param detail ProfessionalDetail
   */
  public void update(ProfessionalDetail detail) {
    this.summary = detail.getSummary();
    this.experiences = detail.getExperiences();
    this.languages = detail.getLanguages();
    this.abilityGroups = detail.getAbilityGroups();
    this.educations = detail.getEducations();
    this.certifications = detail.getCertifications();
    this.links = detail.getLinks();
  }
}

