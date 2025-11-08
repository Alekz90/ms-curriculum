package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidTittleText;
import document.Ability;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public class AbilityDto {

  /**
   * Request DTO for Ability
   */
  @Schema(name = "Ability.Request", description = "Ability Request DTO")
  public record Request(
      @NotBlank @ValidTittleText String name,
      @Digits(integer = 3, fraction = 2) @DecimalMin("0.1") @DecimalMax("100.00") Double percent) { }

  /**
   * Response DTO for Ability
   */
  @Schema(name = "Ability.Response", description = "Ability Response DTO")
  public record Response(String id, String name, Double percent) {

    /**
     * Build AbilityDto.Response from Ability
     * @param ability Ability
     * @return AbilityDto.Response
     */
    public static Response build(Ability ability) {
      return new Response(ability.getId(), ability.getName(), ability.getPercent());
    }
  }
}
