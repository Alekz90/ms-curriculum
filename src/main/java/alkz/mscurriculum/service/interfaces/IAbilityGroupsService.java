package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.AbilityGroupDto;
import alkz.mscurriculum.model.AddressDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface IAbilityGroupsService
    extends IGenericCrudDetailService<String, String, AbilityGroupDto.Request, AbilityGroupDto.Response> {}
