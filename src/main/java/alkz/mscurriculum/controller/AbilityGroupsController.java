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
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Valid @RequestBody AbilityGroupDto.Request request) {
    AbilityGroupDto.Response response = service.create(detailId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(ABILITY_GROUPS + V1_PATH, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<AbilityGroupDto.Response>> updateAbilityGroup(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id,
      @Valid @RequestBody AbilityGroupDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(detailId, id, request)));
  }

  @DeleteMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<Void> deleteAbilityGroup(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id) {
    service.delete(detailId, id);
    return ResponseEntity.noContent().build();
  }
}
