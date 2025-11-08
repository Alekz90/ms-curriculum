package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.AddressDto;
import alkz.mscurriculum.model.ExperienceDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface IExperiencesService
    extends IGenericCrudDetailService<String, String, ExperienceDto.Request, ExperienceDto.Response> {}
