package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidTittleText;
import alkz.mscurriculum.document.AbilityGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * DTO for AbilityGroup
 */
public class AbilityGroupDto {
  /**
   * Request DTO for AbilityGroup
   */
  @Schema(name = "AbilityGroupDto.CreateRequest", description = "DTO Request that it creates AbilityGroup")
  public record CreateRequest(
      @NotBlank @ValidTittleText @Size(max = 100) String name,
      @NotEmpty List<AbilityDto.CreateRequest> abilities) { }

  @Schema(name = "AbilityGroupDto.UpdateRequest", description = "DTO Request that it updates AbilityGroup")
  public record UpdateRequest(
      @NotBlank @ValidTittleText @Size(max = 100) String name,
      @NotEmpty List<AbilityDto.UpdateRequest> abilities) { }

  /**
   * Response DTO for AbilityGroup
   */
  @Schema(name = "AbilityGroupDto.Response", description = "Response DTO for AbilityGroup")
  public record Response(String id, String name, List<AbilityDto.Response> abilities) {

    /**
     * Build AbilityGroupDto.Response from AbilityGroup
     * @param abilityGroup AbilityGroup
     * @return AbilityGroupDto.Response
     */
    public static Response build(AbilityGroup abilityGroup) {
      return new Response(abilityGroup.getId(), abilityGroup.getName(),
          abilityGroup.getAbilities()
              .stream()
              .map(AbilityDto.Response::build)
              .toList());
    }
  }
}
