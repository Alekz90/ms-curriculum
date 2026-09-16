package alkz.mscurriculum.controller;

import akz.commonutils.dto.ResultDto;
import akz.commonutils.util.CommonConstants;
import akz.commonutils.util.CommonUtils;
import alkz.mscurriculum.dto.AddressDto;
import alkz.mscurriculum.service.interfaces.IAddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.ADDRESS;
import static alkz.mscurriculum.util.PathConstants.V1;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(ADDRESS)
@Tag(name = "Address", description = "Address management endpoints")
public class AddressController {

  private final IAddressService service;

  @PostMapping(V1 + "/profiles/{profileId}")
  public ResponseEntity<ResultDto<AddressDto.Response>> createAddress(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String profileId,
      @Valid @RequestBody AddressDto.Request request) {
    AddressDto.Response response = service.create(profileId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(ADDRESS + V1, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1 + "/{id}/profiles/{profileId}")
  public ResponseEntity<ResultDto<AddressDto.Response>> updateAddress(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String profileId,
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
      @Valid @RequestBody AddressDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(profileId, id, request)));
  }
}
