package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alkz.mscurriculum.dto.DetailDto;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.PROFESSIONAL_DETAILS;
import static alkz.mscurriculum.util.PathConstants.V1;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(PROFESSIONAL_DETAILS)
@Tag(name = "Profesional Details", description = "Endpoints for profesional details management")
public class ProfessionalDetailsController {

  private final IProfessionalDetailsService service;

  @GetMapping(V1 + "/{id}")
  public ResponseEntity<ResultDto<DetailDto.Response>> getProfessionalDetailById(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id) {
    return ResponseEntity.ok(new ResultDto<>(service.getDetailById(id)));
  }

  @GetMapping(V1 + "/users/{userId}")
  public ResponseEntity<ResultDto<DetailDto.Response>> getProfessionalDetailByUserId(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String userId) {
    return ResponseEntity.ok(new ResultDto<>(service.getDetailByUserId(userId)));
  }
}
