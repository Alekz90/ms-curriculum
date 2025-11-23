package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.model.AbilityGroupDto;
import alkz.mscurriculum.service.interfaces.IAbilityGroupsService;
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
@RequestMapping(ABILITY_GROUPS)
@Tag(name = "Ability Groups", description = "Endpoints for managing ability groups in professional details")
public class AbilityGroupsController {

  private final IAbilityGroupsService service;

  @PostMapping(V1_PATH + "/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<AbilityGroupDto.Response>> createAbilityGroup(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @Valid @RequestBody AbilityGroupDto.CreateRequest request) {
    AbilityGroupDto.Response response = service.create(detailId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(ABILITY_GROUPS + V1_PATH, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<AbilityGroupDto.Response>> updateAbilityGroup(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
      @Valid @RequestBody AbilityGroupDto.UpdateRequest request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(detailId, id, request)));
  }

  @DeleteMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<Void> deleteAbilityGroup(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id) {
    service.delete(detailId, id);
    return ResponseEntity.noContent().build();
  }
}
