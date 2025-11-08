package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.model.AbilityDto;
import alkz.mscurriculum.service.interfaces.IAbilitiesService;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.enums.EError;
import document.Ability;
import document.ProfessionalDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbilitiesService implements IAbilitiesService {

  private final IProfessionalDetailsService detailsService;

  @Override
  public AbilityDto.Response create(String detailId, String abilityGroupId, AbilityDto.Request request) {
    ProfessionalDetail detail =  detailsService.findDetailById(detailId);

    Ability entity = Ability.build(request);
    detail.getAbilityGroups().parallelStream()
        .filter(ag -> ag.getId().equals(abilityGroupId))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.ABILITY_GROUP_NOT_FOUND))
        .getAbilities()
        .add(entity);
    detailsService.updateDetailData(detail);

    return AbilityDto.Response.build(entity);
  }

  @Override
  public AbilityDto.Response update(String detailId, String abilityGroupId, String id, AbilityDto.Request request) {
    ProfessionalDetail detail =  detailsService.findDetailById(detailId);

    Ability entity = detail.getAbilityGroups().parallelStream()
        .filter(ag -> ag.getId().equals(abilityGroupId))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.LINK_NOT_FOUND))
        .getAbilities()
        .parallelStream()
        .filter(a -> a.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.ABILITY_NOT_FOUND));

    entity.update(request);
    detailsService.updateDetailData(detail);
    return AbilityDto.Response.build(entity);
  }

  @Override
  public void delete(String detailId, String abilityGroupId, String id) {
    ProfessionalDetail detail =  detailsService.findDetailById(detailId);

    boolean removed = detail.getAbilityGroups().parallelStream()
        .filter(ag -> ag.getId().equals(abilityGroupId))
        .findFirst()
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.ABILITY_GROUP_NOT_FOUND))
        .getAbilities()
        .removeIf(a -> a.getId().equals(id));
    if (!removed) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.ABILITY_NOT_FOUND);
    }
    detailsService.updateDetailData(detail);
  }
}
