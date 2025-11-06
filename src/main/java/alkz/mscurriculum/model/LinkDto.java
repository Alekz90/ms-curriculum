package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidSpecialText;
import alejdaf.commonutils.annotation.ValidTextName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LinkDto {
  public record request(
      @NotBlank @ValidIdentifier String idProfesionalExperience,
      @NotBlank @ValidTextName @Size(max = 100) String name,
      @NotBlank @ValidSpecialText @Size(max = 300) String url) {}

  public record response(String idProfesionalExperience, String idLink, String name, String url) {

    public static response build(String idProfesionalExperience, String idLink, String name, String url) {
      return new response(idProfesionalExperience, idLink, name, url);
    }
  }
}
