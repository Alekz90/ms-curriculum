package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidText;
import alkz.mscurriculum.util.enums.ELanguageLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LanguageDto {

  public record Request(
      @NotBlank @ValidIdentifier String idProfesionalExperience,
      @NotBlank @ValidText @Size(max = 100) String name,
      @NotBlank ELanguageLevel level) {}

  public record Response(String idProfesionalExperience, String id, String name, ELanguageLevel level) {

    public static Response build(String idProfesionalExperience, String id, String name, ELanguageLevel level) {
      return new Response(idProfesionalExperience, id, name, level);
    }
  }
}
