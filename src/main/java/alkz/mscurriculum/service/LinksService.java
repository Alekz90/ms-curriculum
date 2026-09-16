package alkz.mscurriculum.service;

import akz.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.Link;
import alkz.mscurriculum.document.ProfessionalDetail;
import alkz.mscurriculum.dto.LinkDto;
import alkz.mscurriculum.service.interfaces.ILinksService;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinksService implements ILinksService {

  private final IProfessionalDetailsService detailsService;

  @Override
  public LinkDto.Response create(String idDetail, LinkDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    Link entity = Link.build(request);
    detail.getLinks().add(entity);
    detailsService.update(detail);

    return LinkDto.Response.build(entity);
  }

  @Override
  public LinkDto.Response update(String idDetail, String id, LinkDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    Link entity = detail.getLinks().parallelStream()
        .filter(l -> l.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.LINK_NOT_FOUND));

    entity.update(request);
    detailsService.update(detail);

    return LinkDto.Response.build(entity);
  }

  @Override
  public void delete(String idDetail, String id) {
    ProfessionalDetail detail =  detailsService.findById(idDetail);

    boolean removed = detail.getLinks().removeIf(l -> l.getId().equals(id));
    if (!removed) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.LINK_NOT_FOUND);
    }

    detailsService.update(detail);
  }
}
