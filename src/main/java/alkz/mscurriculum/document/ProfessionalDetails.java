package alkz.mscurriculum.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "ProfesionalDetails")
public class ProfessionalDetails {

  @MongoId
  private String id;
  private String position;
  private String summary;
  private Set<ProfessionalExperience> profesionalExperiences;
  private Set<Language> languages;
  private Set<AbilityGroup> abilityGroups;
  private Set<Education> educations;
  private Set<Certification> certifications;
  private Set<Link> links;
  private Address address;
}

