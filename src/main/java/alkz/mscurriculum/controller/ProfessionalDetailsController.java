package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.model.ProfessionalDetailDto;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.PROFESSIONAL_DETAILS;
import static alkz.mscurriculum.util.PathConstants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(PROFESSIONAL_DETAILS)
@Tag(name = "Profesional Details", description = "Endpoints for profesional details management")
public class ProfessionalDetailsController {

  private final IProfessionalDetailsService service;

  @GetMapping(V1_PATH + "/{id}")
  public ResponseEntity<ResultDto<ProfessionalDetailDto.Response>> getProfessionalDetailById(String id) {
    return ResponseEntity.ok(new ResultDto<>(service.findById(id)));
  }

  @GetMapping(V1_PATH + "/users/{userId}")
  public ResponseEntity<ResultDto<ProfessionalDetailDto.Response>> getProfessionalDetailByUserId(String userId) {
    return ResponseEntity.ok(new ResultDto<>(service.findByIdUser(userId)));
  }

  @PostMapping(V1_PATH)
  public ResponseEntity<ResultDto<ProfessionalDetailDto.Response>> createProfessionalDetail(ProfessionalDetailDto.Request request) {
    ProfessionalDetailDto.Response response = service.create(request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(PROFESSIONAL_DETAILS + V1_PATH, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1_PATH + "/{id}")
  public ResponseEntity<ResultDto<ProfessionalDetailDto.Response>> updateProfessionalDetail(
      @PathVariable String id,
      @RequestBody ProfessionalDetailDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(id, request)));
  }
}
