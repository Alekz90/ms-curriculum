package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.dto.CertificationDto;
import alkz.mscurriculum.service.interfaces.ICertificationsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.CERTIFICATIONS;
import static alkz.mscurriculum.util.PathConstants.V1;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(CERTIFICATIONS)
@Tag(name = "Certifications", description = "Manage certifications in professional details")
public class CertificationsController {

  private final ICertificationsService service;

  @PostMapping(V1 + "/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<CertificationDto.Response>> createCertification(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @Valid @RequestBody CertificationDto.Request request) {
    CertificationDto.Response response = service.create(detailId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(CERTIFICATIONS + V1, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1 + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<CertificationDto.Response>> updateCertification(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
      @Valid @RequestBody CertificationDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(detailId, id, request)));
  }

  @DeleteMapping(V1 + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<Void> deleteCertification(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String detailId,
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id) {
    service.delete(detailId, id);
    return ResponseEntity.noContent().build();
  }
}
