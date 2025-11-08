package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import document.Certification;
import document.ProfessionalDetail;
import alkz.mscurriculum.model.CertificationDto;
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
    ProfessionalDetail detailEntity =  detailsService.findDetailById(idDetail);

    Certification entity = Certification.build(request);
    detailEntity.getCertifications().add(entity);
    detailsService.updateDetailData(detailEntity);

    return CertificationDto.Response.build(entity);
  }

  @Override
  public CertificationDto.Response update(String idDetail, String id, CertificationDto.Request request) {
    ProfessionalDetail detailEntity =  detailsService.findDetailById(idDetail);

    Certification entity = detailEntity.getCertifications().parallelStream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.CERTIFICATION_NOT_FOUND));

    entity.update(request);
    detailsService.updateDetailData(detailEntity);

    return CertificationDto.Response.build(entity);
  }

  @Override
  public void delete(String idDetail, String id) {
    ProfessionalDetail detailEntity =  detailsService.findDetailById(idDetail);

    boolean removed = detailEntity.getCertifications().removeIf(c -> c.getId().equals(id));
    if (!removed) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.CERTIFICATION_NOT_FOUND);
    }

    detailsService.updateDetailData(detailEntity);
  }
}
