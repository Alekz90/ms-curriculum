package alkz.mscurriculum.service;

import akz.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.ProfessionalDetail;
import alkz.mscurriculum.document.ProfessionalExperience;
import alkz.mscurriculum.dto.ExperienceDto;
import alkz.mscurriculum.service.interfaces.IExperiencesService;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExperiencesService implements IExperiencesService {

  private final IProfessionalDetailsService detailsService;

  @Override
  public ExperienceDto.Response create(String idDetail, ExperienceDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    ProfessionalExperience entity = ProfessionalExperience.build(request);
    detail.getExperiences().add(entity);
    detailsService.update(detail);

    return ExperienceDto.Response.build(entity);
  }

  @Override
  public ExperienceDto.Response update(String idDetail, String id, ExperienceDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    ProfessionalExperience entity = detail.getExperiences().parallelStream()
        .filter(pe -> pe.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.EXPERIENCE_NOT_FOUND));

    entity.update(request);
    detailsService.update(detail);

    return ExperienceDto.Response.build(entity);
  }

  @Override
  public void delete(String idDetail, String id) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    boolean removed = detail.getExperiences().removeIf(pe -> pe.getId().equals(id));
    if (!removed) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.EXPERIENCE_NOT_FOUND);
    }

    detailsService.update(detail);
  }
}
