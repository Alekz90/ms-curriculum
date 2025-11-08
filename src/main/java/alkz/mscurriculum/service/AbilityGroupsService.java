package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.model.AbilityGroupDto;
import alkz.mscurriculum.service.interfaces.IAbilityGroupsService;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.enums.EError;
import document.AbilityGroup;
import document.ProfessionalDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbilityGroupsService implements IAbilityGroupsService {

  private final IProfessionalDetailsService detailsService;

  @Override
  public AbilityGroupDto.Response create(String detailId, AbilityGroupDto.Request request) {
    ProfessionalDetail detail =  detailsService.findDetailById(detailId);

    AbilityGroup entity = AbilityGroup.build(request);
    detail.getAbilityGroups().add(entity);
    detailsService.updateDetailData(detail);

    return AbilityGroupDto.Response.build(entity);
  }

  @Override
  public AbilityGroupDto.Response update(String detailId, String id, AbilityGroupDto.Request request) {
    ProfessionalDetail detail =  detailsService.findDetailById(detailId);

    AbilityGroup entity = detail.getAbilityGroups().parallelStream()
        .filter(ag -> ag.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.LINK_NOT_FOUND));

    entity.update(request);
    detailsService.updateDetailData(detail);

    return AbilityGroupDto.Response.build(entity);
  }

  @Override
  public void delete(String detailId, String id) {
    ProfessionalDetail detail =  detailsService.findDetailById(detailId);

    boolean removed = detail.getAbilityGroups().removeIf(ag -> ag.getId().equals(id));
    if (!removed) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.LINK_NOT_FOUND);
    }

    detailsService.updateDetailData(detail);
  }
}
