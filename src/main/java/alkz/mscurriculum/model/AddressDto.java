package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidText;
import alkz.mscurriculum.document.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddressDto {

  public record Request(
    @NotBlank @ValidText @Size(max = 100) String country,
    @NotBlank @ValidText @Size(max = 100) String state,
    @NotBlank @ValidText @Size(max = 100) String city) {}

  public record Response(String id, String country, String state, String city) {
    public static Response build(Address address) {
      return new Response(address.getId(), address.getCountry(), address.getState(), address.getCity());
    }
  }
}
