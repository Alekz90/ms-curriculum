package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.dto.ExperienceDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface IExperiencesService
    extends IGenericCrudDetailService<String, String, ExperienceDto.Request, ExperienceDto.Request, ExperienceDto.Response> {}
