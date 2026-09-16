package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.dto.CertificationDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface ICertificationsService
    extends IGenericCrudDetailService<String, String, CertificationDto.Request, CertificationDto.Request, CertificationDto.Response> {}
