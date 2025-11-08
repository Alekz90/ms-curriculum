package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.model.AddressDto;
import alkz.mscurriculum.service.interfaces.IAddressService;
import alkz.mscurriculum.service.interfaces.IUsersService;
import alkz.mscurriculum.util.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.ADDRESS;
import static alkz.mscurriculum.util.PathConstants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(ADDRESS)
@Tag(name = "Address", description = "Address management endpoints")
public class AddressController {

  private final IAddressService service;

  @PostMapping(V1_PATH)
  public ResponseEntity<ResultDto<AddressDto.Response>> createAddress(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String idDetail,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id,
      @Valid @RequestBody AddressDto.Request request) {
    AddressDto.Response response = service.create(idDetail, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(ADDRESS + V1_PATH, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1_PATH + "/{id}/profesional-details/{detailId}")
  public ResponseEntity<ResultDto<AddressDto.Response>> updateAddress(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String idDetail,
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id,
      @Valid @RequestBody AddressDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(idDetail, id, request)));
  }
}
