package alkz.mscurriculum.controller;

import alejdaf.commonutils.util.CommonConstants;
import alkz.mscurriculum.service.interfaces.IVerificationsService;
import alkz.mscurriculum.util.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.V1_PATH;
import static alkz.mscurriculum.util.PathConstants.VERIFICATIONS;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(VERIFICATIONS)
@Tag(name = "Verifications", description = "Endpoints for user verifications")
public class VerificationsController {

  private final IVerificationsService service;

  @GetMapping(V1_PATH + "/{id}/verify/{code}" )
  public ResponseEntity<Void> verifyAccount(@PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
                                            @PathVariable @Pattern(regexp = Constants.VERIFICATION_CODE_PATTERN) String code) {
    service.markVerificationAsUsed(id, code);
    return ResponseEntity.noContent().build();
  }
}
