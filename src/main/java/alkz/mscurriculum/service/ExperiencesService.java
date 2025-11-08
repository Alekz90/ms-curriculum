package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.model.ExperienceDto;
import alkz.mscurriculum.service.interfaces.IExperiencesService;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.enums.EError;
import document.ProfessionalExperience;
import document.ProfessionalDetail;
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
    ProfessionalDetail detail =  detailsService.findDetailById(idDetail);

    ProfessionalExperience entity = ProfessionalExperience.build(request);
    detail.getProfesionalExperiences().add(entity);
    detailsService.updateDetailData(detail);

    return ExperienceDto.Response.build(entity);
  }

  @Override
  public ExperienceDto.Response update(String idDetail, String id, ExperienceDto.Request request) {
    ProfessionalDetail detail =  detailsService.findDetailById(idDetail);

    ProfessionalExperience entity = detail.getProfesionalExperiences().parallelStream()
        .filter(pe -> pe.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.EXPERIENCE_NOT_FOUND));

    entity.update(request);
    detailsService.updateDetailData(detail);

    return ExperienceDto.Response.build(entity);
  }

  @Override
  public void delete(String idDetail, String id) {
    ProfessionalDetail detail =  detailsService.findDetailById(idDetail);

    boolean removed = detail.getProfesionalExperiences().removeIf(pe -> pe.getId().equals(id));
    if (!removed) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.EXPERIENCE_NOT_FOUND);
    }

    detailsService.updateDetailData(detail);
  }
}
