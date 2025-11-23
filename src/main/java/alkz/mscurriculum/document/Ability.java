package alkz.mscurriculum.document;

import alkz.mscurriculum.model.AbilityDto;
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
public class Ability {

  @MongoId
  private String id;
  private String name;
  private Double percent;

  /**
   * Build Ability from AbilityDto.CreateRequest
   * @param request AbilityDto.CreateRequest
   * @return Ability
   */
  public static Ability build(AbilityDto.CreateRequest request) {
    return new Ability(Utils.generateObjectId(), request.name(), request.percent());
  }

  /**
   * Build Ability from AbilityDto.UpdateRequest
   * @param request AbilityDto.UpdateRequest
   * @return Ability
   */
  public static Ability build(AbilityDto.UpdateRequest request) {
    return new Ability(request.id(), request.name(), request.percent());
  }
}
