package alkz.mscurriculum.service;

import akz.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.Certification;
import alkz.mscurriculum.document.ProfessionalDetail;
import alkz.mscurriculum.dto.CertificationDto;
import alkz.mscurriculum.service.interfaces.ICertificationsService;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CertificationsService implements ICertificationsService {

  private final IProfessionalDetailsService detailsService;

  @Override
  public CertificationDto.Response create(String idDetail, CertificationDto.Request request) {
    ProfessionalDetail detailEntity =  detailsService.findById(idDetail);

    Certification entity = Certification.build(request);
    detailEntity.getCertifications().add(entity);
    detailsService.update(detailEntity);

    return CertificationDto.Response.build(entity);
  }

  @Override
  public CertificationDto.Response update(String idDetail, String id, CertificationDto.Request request) {
    ProfessionalDetail detailEntity =  detailsService.findById(idDetail);

    Certification entity = detailEntity.getCertifications().parallelStream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.CERTIFICATION_NOT_FOUND));

    entity.update(request);
    detailsService.update(detailEntity);

    return CertificationDto.Response.build(entity);
  }

  @Override
  public void delete(String idDetail, String id) {
    ProfessionalDetail detailEntity =  detailsService.findById(idDetail);

    boolean removed = detailEntity.getCertifications().removeIf(c -> c.getId().equals(id));
    if (!removed) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.CERTIFICATION_NOT_FOUND);
    }

    detailsService.update(detailEntity);
  }
}
