package alkz.mscurriculum.dto;

import alejdaf.commonutils.annotation.ValidNameText;
import alkz.mscurriculum.util.enums.ELanguageLevel;
import alkz.mscurriculum.document.Language;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LanguageDto {

  /**
   * Request DTO for Language
   */
  @Schema(name = "LanguageDto.Request", description = "DTO for Language")
  public record Request(
      @NotBlank @ValidNameText @Size(max = 100) String name,
      @NotNull ELanguageLevel level) {}

  /**
   * Response DTO for Language
   */
  @Schema(name = "LanguageDto.Response", description = "DTO for Language")
  public record Response(String id, String name, ELanguageLevel level) {

    /**
     * Build a Response DTO from Language document
     * @param language Language
     */
    @Schema(description = "Build a Response DTO from Language document")
    public static Response build(Language language) {
      return new Response(language.getId(), language.getName(), language.getLevel());
    }
  }
}
