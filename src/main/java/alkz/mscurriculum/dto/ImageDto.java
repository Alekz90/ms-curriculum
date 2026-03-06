package alkz.mscurriculum.dto;

import alejdaf.commonutils.annotation.ValidSpecialText;
import alejdaf.commonutils.annotation.ValidTittleText;
import alkz.mscurriculum.document.Image;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ImageDto {

  @Schema(name = "ImageDto.Request", description = "DTO for image request")
  public record Request(
      @NotBlank @ValidTittleText @Size(max = 100) String name,
      @NotBlank @ValidSpecialText @Size(max = 300) String url) {}

  @Schema(name = "ImageDto.Response", description = "DTO for image response")
  public record Response(String id, String name, String url) {

    public static Response build(Image image) {
      return new Response(image.getId(), image.getName(), image.getUrl());
    }
  }
}
