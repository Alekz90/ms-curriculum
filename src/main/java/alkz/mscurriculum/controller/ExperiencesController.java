package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.model.ExperienceDto;
import alkz.mscurriculum.service.interfaces.IExperiencesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.EXPERIENCES;
import static alkz.mscurriculum.util.PathConstants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(EXPERIENCES)
@Tag(name = "Experiences", description = "Endpoints for managing experiences in professional details")
public class ExperiencesController {

  private final IExperiencesService service;

  @PostMapping(V1_PATH + "/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<ExperienceDto.Response>> createExperience(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Valid @RequestBody ExperienceDto.Request request) {
    ExperienceDto.Response response = service.create(detailId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(EXPERIENCES + V1_PATH, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<ExperienceDto.Response>> updateExperience(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id,
      @Valid @RequestBody ExperienceDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(detailId, id, request)));
  }

  @DeleteMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<Void> deleteExperience(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id) {
    service.delete(detailId, id);
    return ResponseEntity.noContent().build();
  }
}
