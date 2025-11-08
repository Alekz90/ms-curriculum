package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidTittleText;
import document.AbilityGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for AbilityGroup
 */
public class AbilityGroupDto {
  /**
   * Request DTO for AbilityGroup
   */
  @Schema(name = "AbilityGroupDto.Request", description = "Request DTO for AbilityGroup")
  public record Request(@NotBlank @ValidTittleText @Size(max = 100) String name) { }

  /**
   * Response DTO for AbilityGroup
   */
  @Schema(name = "AbilityGroupDto.Response", description = "Response DTO for AbilityGroup")
  public record Response(String id, String name) {

    /**
     * Build AbilityGroupDto.Response from AbilityGroup
     * @param abilityGroup AbilityGroup
     * @return AbilityGroupDto.Response
     */
    public static Response build(AbilityGroup abilityGroup) {
      return new Response(abilityGroup.getId(), abilityGroup.getName());
    }
  }
}
