package alkz.mscurriculum.document;

import alkz.mscurriculum.dto.AbilityGroupDto;
import alkz.mscurriculum.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbilityGroup {

  @MongoId
  private String id;
  private String name;
  private List<Ability> abilities;

  /**
   * Build AbilityGroup from AbilityGroupDto.Request
   * @param request AbilityGroupDto.Request
   * @return AbilityGroup
   */
  public static AbilityGroup build(AbilityGroupDto.CreateRequest request) {
    return AbilityGroup.builder()
        .id(Utils.generateObjectId())
        .name(request.name())
        .abilities(request.abilities()
                    .stream()
                    .map(Ability::build)
                    .toList())
        .build();
  }

  /**
   * Update AbilityGroup from AbilityGroupDto.Request
   * @param request AbilityGroupDto.UpdateRequest
   */
  public void update(AbilityGroupDto.UpdateRequest request) {
    this.name = request.name();
    this.abilities = request.abilities()
        .stream()
        .map(Ability::build)
        .toList();
  }
}

