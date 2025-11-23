package alkz.mscurriculum.document;

import alkz.mscurriculum.dto.AddressDto;
import alkz.mscurriculum.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

  @MongoId
  private String id;
  private String country;
  private String state;
  private String city;
  private Boolean showInCurriculum;

  /**
   * Build Address from AddressDto.Request
   * @param request AddressDto.Request
   * @return Address
   */
  public static Address build(AddressDto.Request request) {
    return Address.builder()
        .id(Utils.generateObjectId())
        .country(request.country())
        .state(request.state())
        .city(request.city())
        .showInCurriculum(request.showInCurriculum())
        .build();
  }

  /**
   * Update Address from AddressDto.Request
   * @param request AddressDto.Request
   */
  public void update(AddressDto.Request request) {
    this.country = request.country();
    this.state = request.state();
    this.city = request.city();
    this.showInCurriculum = request.showInCurriculum();
  }
}
