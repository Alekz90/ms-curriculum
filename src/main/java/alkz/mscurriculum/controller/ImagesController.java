package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.dto.ImageDto;
import alkz.mscurriculum.service.interfaces.IImagesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.IMAGES;
import static alkz.mscurriculum.util.PathConstants.V1;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(IMAGES)
@Tag(name = "Images", description = "Images management endpoints")
public class ImagesController {

  private final IImagesService service;

  @PostMapping(V1 + "/profiles/{profileId}")
  public ResponseEntity<ResultDto<ImageDto.Response>> createImages(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String profileId,
      @Valid @RequestBody ImageDto.Request request) {
    ImageDto.Response response = service.create(profileId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(IMAGES + V1, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1 + "/{id}/profiles/{profileId}")
  public ResponseEntity<ResultDto<ImageDto.Response>> updateImage(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String profileId,
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
      @Valid @RequestBody ImageDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(profileId, id, request)));
  }
}
