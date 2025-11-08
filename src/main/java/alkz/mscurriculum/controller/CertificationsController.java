package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.model.CertificationDto;
import alkz.mscurriculum.service.interfaces.ICertificationsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.CERTIFICATIONS;
import static alkz.mscurriculum.util.PathConstants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(CERTIFICATIONS)
@Tag(name = "Certifications", description = "Manage certifications in professional details")
public class CertificationsController {

  private final ICertificationsService service;

  @PostMapping(V1_PATH + "/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<CertificationDto.Response>> createCertification(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Valid @RequestBody CertificationDto.Request request) {
    CertificationDto.Response response = service.create(detailId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(CERTIFICATIONS + V1_PATH, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<CertificationDto.Response>> updateCertification(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id,
      @Valid @RequestBody CertificationDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(detailId, id, request)));
  }

  @DeleteMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<Void> deleteCertification(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id) {
    service.delete(detailId, id);
    return ResponseEntity.noContent().build();
  }
}
