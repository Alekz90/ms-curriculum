package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidTextName;
import alkz.mscurriculum.document.Ability;
import jakarta.validation.constraints.*;

public class AbilityDto {

  public record Request(
      @NotBlank @ValidIdentifier String idProfesionalExperience,
      @NotBlank @ValidIdentifier String idAbilityGroup,
      @NotBlank @ValidTextName String name,
      @Digits(integer = 3, fraction = 2) @DecimalMin("0.1") @DecimalMax("100.00") Double percent) { }

  public record Response(
      String idProfesionalExperience,
      String idAbilityGroup,
      String id,
      String name,
      Double percent) {

    public static Response build(String idProfesionalExperience, String idAbilityGroup, Ability ability) {
      return new Response(
          idProfesionalExperience,
          idAbilityGroup,
          ability.getId(),
          ability.getName(),
          ability.getPercent()
      );
    }
  }
}
