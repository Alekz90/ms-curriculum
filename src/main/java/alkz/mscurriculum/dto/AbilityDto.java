package alkz.mscurriculum.dto;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidTittleText;
import alkz.mscurriculum.document.Ability;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class AbilityDto {

  /**
   * Request DTO for Ability
   */
  @Schema(name = "Ability.Request", description = "Ability Request DTO")
  public record CreateRequest(
      @NotBlank @ValidTittleText @Size(max = 100) String name,
      @Digits(integer = 3, fraction = 2) @DecimalMin("0.0a") @DecimalMax("100.00") Double percentage) { }

  /**
   * Update DTO for Ability
   */
  @Schema(name = "Ability.UpdateRequest", description = "Ability Update Request DTO")
  public record UpdateRequest(
      @ValidIdentifier String id,
      @NotBlank @ValidTittleText @Size(max = 100) String name,
      @Digits(integer = 3, fraction = 2) @DecimalMin("0.0") @DecimalMax("100.00") Double percentage) { }

  /**
   * Response DTO for Ability
   */
  @Schema(name = "Ability.Response", description = "Ability Response DTO")
  public record Response(String id, String name, Double percentage) {

    /**
     * Build AbilityDto.Response from Ability
     * @param ability Ability
     * @return AbilityDto.Response
     */
    public static Response build(Ability ability) {
      return new Response(ability.getId(), ability.getName(), ability.getPercentage());
    }
  }
}
