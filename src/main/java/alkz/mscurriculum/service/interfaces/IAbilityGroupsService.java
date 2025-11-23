package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.AbilityGroupDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface IAbilityGroupsService
    extends IGenericCrudDetailService<String, String, AbilityGroupDto.CreateRequest, AbilityGroupDto.UpdateRequest, AbilityGroupDto.Response> {
}
