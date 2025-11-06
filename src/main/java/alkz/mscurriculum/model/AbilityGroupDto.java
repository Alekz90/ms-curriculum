package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidTextName;
import alkz.mscurriculum.document.AbilityGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AbilityGroupDto {
  public record request(
      @NotBlank @ValidIdentifier String idProfesionalExperience,
      @NotBlank @ValidTextName @Size(max = 100) String name) { }

  public record Response(String idProfesionalExperience, String id, String name) {

    public static Response build(AbilityGroup abilityGroup) {
      return new Response(abilityGroup.getId(), abilityGroup.getId(), abilityGroup.getName());
    }
  }
}
