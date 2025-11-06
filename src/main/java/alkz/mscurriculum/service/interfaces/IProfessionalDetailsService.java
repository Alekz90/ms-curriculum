package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.ProfessionalDetailDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudService;

public interface IProfessionalDetailsService
    extends IGenericCrudService<ProfessionalDetailDto.Request, ProfessionalDetailDto.Response, String> {
}
