package document;

import alkz.mscurriculum.model.AbilityGroupDto;
import alkz.mscurriculum.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Document(collection = "AbilityGroups")
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
  public static AbilityGroup build(AbilityGroupDto.Request request) {
    return AbilityGroup.builder()
        .id(Utils.generateObjectId())
        .name(request.name())
        .abilities(new ArrayList<>())
        .build();
  }

  /**
   * Update AbilityGroup from AbilityGroupDto.Request
   * @param request AbilityGroupDto.Request
   */
  public void update(AbilityGroupDto.Request request) {
    this.name = request.name();
  }
}

