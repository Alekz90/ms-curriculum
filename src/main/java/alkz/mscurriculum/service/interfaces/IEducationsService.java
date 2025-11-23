package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.EducationDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface IEducationsService
    extends IGenericCrudDetailService<String, String, EducationDto.Request, EducationDto.Request, EducationDto.Response> {}
