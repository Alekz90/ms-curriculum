package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.document.ProfessionalDetail;
import alkz.mscurriculum.model.DetailDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudService;

public interface IProfessionalDetailsService {
  ProfessionalDetail create(String userId);
  ProfessionalDetail update(ProfessionalDetail detail);
  ProfessionalDetail findById(String id);
  ProfessionalDetail findByUserId(String userId);
  DetailDto.Response getDetailById(String id);
  DetailDto.Response getDetailByUserId(String userId);
}
