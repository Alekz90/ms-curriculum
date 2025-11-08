package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.AbilityDto;

public interface IAbilitiesService {
  AbilityDto.Response create(String detailId, String abilityGroupId, AbilityDto.Request request);
  AbilityDto.Response update(String detailId, String abilityGroupId, String id, AbilityDto.Request request);
  void delete(String detailId, String abilityGroupId, String id);
}
