package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.ProfileDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudService;

public interface IProfilesService extends IGenericCrudService<ProfileDto.Request, ProfileDto.Response, String> {}
