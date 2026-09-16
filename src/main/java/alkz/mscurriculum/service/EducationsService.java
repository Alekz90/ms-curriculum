package alkz.mscurriculum.service;

import akz.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.dto.EducationDto;
import alkz.mscurriculum.service.interfaces.IEducationsService;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.enums.EError;
import alkz.mscurriculum.document.Education;
import alkz.mscurriculum.document.ProfessionalDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EducationsService implements IEducationsService {

  private final IProfessionalDetailsService detailsService;

  @Override
  public EducationDto.Response create(String idDetail, EducationDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    Education entity = Education.build(request);
    detail.getEducations().add(entity);
    detailsService.update(detail);

    return EducationDto.Response.build(entity);
  }

  @Override
  public EducationDto.Response update(String idDetail, String id, EducationDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    Education entity = detail.getEducations().parallelStream()
        .filter(e -> e.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.EDUCATION_NOT_FOUND));

    entity.update(request);
    detailsService.update(detail);

    return EducationDto.Response.build(entity);
  }

  @Override
  public void delete(String idDetail, String id) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    boolean removed = detail.getEducations().removeIf(e -> e.getId().equals(id));
    if (!removed) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.EDUCATION_NOT_FOUND);
    }

    detailsService.update(detail);
  }
}
