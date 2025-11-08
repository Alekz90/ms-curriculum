package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.model.LanguageDto;
import alkz.mscurriculum.service.interfaces.ILanguagesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.LANGUAGES;
import static alkz.mscurriculum.util.PathConstants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(LANGUAGES)
@Tag(name = "Languages", description = "Endpoints for managing languages in professional details")
public class LanguagesController {

  private final ILanguagesService service;

  @PostMapping(V1_PATH + "/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<LanguageDto.Response>> createLanguage(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Valid @RequestBody LanguageDto.Request request) {
    LanguageDto.Response response = service.create(detailId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(LANGUAGES + V1_PATH, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<LanguageDto.Response>> updateLanguage(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id,
      @Valid @RequestBody LanguageDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(detailId, id, request)));
  }

  @DeleteMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<Void> deleteLanguage(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String detailId,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id) {
    service.delete(detailId, id);
    return ResponseEntity.noContent().build();
  }
}
