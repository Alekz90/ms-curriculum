package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.CertificationDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface ICertificationsService
    extends IGenericCrudDetailService<String, String, CertificationDto.Request, CertificationDto.Response> {}
