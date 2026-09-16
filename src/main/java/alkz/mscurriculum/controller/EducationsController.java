package alkz.mscurriculum.controller;

import akz.commonutils.dto.ResultDto;
import akz.commonutils.util.CommonConstants;
import akz.commonutils.util.CommonUtils;
import alkz.mscurriculum.dto.EducationDto;
import alkz.mscurriculum.service.interfaces.IEducationsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.EDUCATIONS;
import static alkz.mscurriculum.util.PathConstants.V1;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(EDUCATIONS)
@Tag(name = "Educations", description = "Endpoints for managing educations in professional details")
public class EducationsController {

  private final IEducationsService service;

  @PostMapping(V1 + "/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<EducationDto.Response>> createEducation(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN)  String detailId,
      @Valid @RequestBody EducationDto.Request request) {
    EducationDto.Response response = service.create(detailId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(EDUCATIONS + V1, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1 + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<EducationDto.Response>> updateEducation(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
      @Valid @RequestBody EducationDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(detailId, id, request)));
  }

  @DeleteMapping(V1 + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<Void> deleteEducation(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id) {
    service.delete(detailId, id);
    return ResponseEntity.noContent().build();
  }
}
