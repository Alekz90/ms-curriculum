package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.model.SummaryDto;
import alkz.mscurriculum.service.interfaces.ISummariesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.SUMMARIES;
import static alkz.mscurriculum.util.PathConstants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SUMMARIES)
@Tag(name = "Summaries", description = "Endpoints for managing summaries")
public class SummariesController {

  private final ISummariesService service;

  @PostMapping(V1_PATH + "/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<SummaryDto.Response>> createSummary(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @Valid @RequestBody SummaryDto.Request request) {
    SummaryDto.Response response = service.create(detailId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(SUMMARIES + V1_PATH, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<SummaryDto.Response>> updateSummary(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
      @Valid @RequestBody SummaryDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(detailId, id, request)));
  }

  @DeleteMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<Void> deleteSummary(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id) {
    service.delete(detailId, id);
    return ResponseEntity.noContent().build();
  }
}
