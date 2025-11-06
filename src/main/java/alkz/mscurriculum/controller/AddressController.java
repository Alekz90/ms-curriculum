package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.model.AddressDto;
import alkz.mscurriculum.service.interfaces.IAddressService;
import alkz.mscurriculum.service.interfaces.IUsersService;
import alkz.mscurriculum.util.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
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

  @GetMapping(V1_PATH + "/{id}")
  public ResponseEntity<ResultDto<AddressDto.Response>> getAddressById(String id) {
    return ResponseEntity.ok(new ResultDto<>(service.findById(id)));
  }

  @GetMapping(V1_PATH + "/professional-details/{detailId}")
  public ResponseEntity<ResultDto<AddressDto.Response>> getAddressByProfessionalDetailId(String detailId) {
    return ResponseEntity.ok(new ResultDto<>(service.findByIdProfesionalDetail(detailId)));
  }

  @PostMapping(V1_PATH)
  public ResponseEntity<ResultDto<AddressDto.Response>> createAddress(AddressDto.Request request) {
    AddressDto.Response response = service.create(request);
    return ResponseEntity.created(CommonUtils.buildUriPost(ADDRESS + V1_PATH, response.id())).body(new ResultDto<>());
  }

  @PutMapping(V1_PATH + "/{id}")
  public ResponseEntity<ResultDto<AddressDto.Response>> updateAddress(String id, AddressDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(id, request)));
  }

  @DeleteMapping(V1_PATH + "/{id}")
  public ResponseEntity<Void> deleteAddress(String id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

}
