package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.model.AbilityDto;
import alkz.mscurriculum.service.interfaces.IAbilitiesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(ABILITIES)
@Tag(name = "Abilities", description = "Endpoints for managing abilities and certifications")
public class AbilitiesController {

  private final IAbilitiesService service;

  @PostMapping(V1_PATH + "/profesional-details/{detailId}/ability-groups/{abilityGroupId}")
  public ResponseEntity<ResultDto<AbilityDto.Response>> createAbility(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String abilityGroupId,
      @Valid @RequestBody AbilityDto.Request request) {
    AbilityDto.Response response = service.create(detailId, abilityGroupId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(ABILITIES + V1_PATH, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1_PATH + "/{id}/profesional-details/{detailId}/ability-groups/{abilityGroupId}")
  public ResponseEntity<ResultDto<AbilityDto.Response>> updateAbility(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String abilityGroupId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id,
      @Valid @RequestBody AbilityDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(detailId, abilityGroupId, id, request)));
  }

  @DeleteMapping(V1_PATH + "/{id}/profesional-details/{detailId}/ability-groups/{abilityGroupId}")
  public ResponseEntity<Void> deleteAbility(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String abilityGroupId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id) {
    service.delete(detailId, abilityGroupId, id);
    return ResponseEntity.noContent().build();
  }
}
