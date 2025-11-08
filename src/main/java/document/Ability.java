package document;

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
//@Document(collection = "Abilities")
public class Ability {

  @MongoId
  private String id;
  private String name;
  private Double percent;

  /**
   * Build Ability from AbilityDto.Request
   * @param request AbilityDto.Request
   * @return Ability
   */
  public static Ability build(AbilityDto.Request request) {
    return Ability.builder()
        .id(Utils.generateObjectId())
        .name(request.name())
        .percent(request.percent())
        .build();
  }

  /**
   * Update Ability from AbilityDto.Request
   * @param request AbilityDto.Request
   */
  public void update(AbilityDto.Request request) {
    this.name = request.name();
    this.percent = request.percent();
  }
}
