package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alejdaf.commonutils.util.enums.ECommonError;
import alkz.mscurriculum.document.ProfessionalDetail;
import alkz.mscurriculum.document.Summary;
import alkz.mscurriculum.dto.SummaryDto;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.service.interfaces.ISummariesService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SummariesService implements ISummariesService {

  private final IProfessionalDetailsService detailsService;

  @Override
  public SummaryDto.Response create(String idDetail, SummaryDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    Summary entity = Summary.build(request);
    detail.setSummary(entity);
    detailsService.update(detail);

    return SummaryDto.Response.build(entity);
  }

  @Override
  public SummaryDto.Response update(String idDetail, String id, SummaryDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);
    if (detail.getSummary() == null || !id.equals(detail.getSummary().getId())) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.SUMMARY_NOT_FOUND);
    }

    detail.getSummary().update(request);
    detailsService.update(detail);

    return SummaryDto.Response.build(detail.getSummary());
  }

  @Override
  public void delete(String s, String s2) {
    throw new CustomCommonException(HttpStatus.NOT_IMPLEMENTED, ECommonError.METHOD_NOT_IMPLEMENTED);
  }
}
