package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.Language;
import alkz.mscurriculum.document.ProfessionalDetail;
import alkz.mscurriculum.model.LanguageDto;
import alkz.mscurriculum.service.interfaces.ILanguagesService;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LanguagesService implements ILanguagesService {

  private final IProfessionalDetailsService detailsService;

  @Override
  public LanguageDto.Response create(String idDetail, LanguageDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    Language entity = Language.build(request);
    detail.getLanguages().add(entity);
    detailsService.update(detail);

    return LanguageDto.Response.build(entity);
  }

  @Override
  public LanguageDto.Response update(String idDetail, String id, LanguageDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    Language entity = detail.getLanguages().parallelStream()
        .filter(l -> l.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.LANGUAGE_NOT_FOUND));

    entity.update(request);
    detailsService.update(detail);

    return LanguageDto.Response.build(entity);
  }

  @Override
  public void delete(String idDetail, String id) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    boolean removed = detail.getLanguages().removeIf(l -> l.getId().equals(id));
    if (!removed) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.LANGUAGE_NOT_FOUND);
    }

    detailsService.update(detail);
  }
}
