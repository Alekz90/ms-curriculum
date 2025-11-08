package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidNameText;
import document.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddressDto {

  /**
   * Request record for AddressDto
   */
  @Schema(name = "AddressDto.Request", description = "Address Request DTO")
  public record Request(
    @NotBlank @ValidNameText @Size(max = 100) String country,
    @NotBlank @ValidNameText @Size(max = 100) String state,
    @NotBlank @ValidNameText @Size(max = 100) String city,
    @NotNull Boolean showInCurriculum
  ) {}

  /**
   * Response record for AddressDto
   */
  @Schema(name = "AddressDto.Response", description = "Address Response DTO")
  public record Response(String id, String country, String state, String city, Boolean showInCurriculum) {

    /**
     * Build AddressDto.Response from Address
     * @param address Address
     * @return AddressDto.Response
     */
    public static Response build(Address address) {
      return new Response(
          address.getId(),
          address.getCountry(),
          address.getState(),
          address.getCity(),
          address.getShowInCurriculum());
    }
  }
}
