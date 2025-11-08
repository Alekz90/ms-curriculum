package alkz.mscurriculum.service.interfaces;

import document.ProfessionalDetail;
import alkz.mscurriculum.model.DetailDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudService;

public interface IProfessionalDetailsService
    extends IGenericCrudService<DetailDto.Request, DetailDto.Response, String> {
  ProfessionalDetail updateDetailData(ProfessionalDetail detail);
  ProfessionalDetail findDetailById(String id);
}
