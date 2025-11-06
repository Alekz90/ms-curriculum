package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidSpecialText;
import alejdaf.commonutils.annotation.ValidTextName;
import alkz.mscurriculum.document.Certification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CertificationDto {

  public record Request(
      @NotBlank @ValidIdentifier String idProfesionalExperience,
      @NotBlank @ValidTextName @Size(max = 100) String name,
      @NotBlank @ValidSpecialText @Size(max = 300) String description) {}

  public record Response(String idProfesionalExperience, String id, String name, String description) {

    public static Response fromDocument(String idProfesionalExperience, Certification certification) {
      return new Response(
          idProfesionalExperience,
          certification.getId(),
          certification.getName(),
          certification.getDescription());
    }
  }
}
