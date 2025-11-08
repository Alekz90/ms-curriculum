package document;

import alkz.mscurriculum.model.CertificationDto;
import alkz.mscurriculum.util.Utils;
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

  /**
   * Create a new certification from CertificationDto.Request
   * @param request CertificationDto.Request
   * @return Certification
   */
  public static Certification build(CertificationDto.Request request) {
    return Certification.builder()
        .id(Utils.generateObjectId())
        .name(request.name())
        .description(request.description())
        .build();
  }

  /**
   * Update certification data
   * @param request CertificationDto.Request
   */
  public void update(CertificationDto.Request request) {
    this.name = request.name();
    this.description = request.description();
  }
}

