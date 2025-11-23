package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alejdaf.commonutils.util.enums.ECommonError;
import alkz.mscurriculum.document.Address;
import alkz.mscurriculum.document.ProfessionalDetail;
import alkz.mscurriculum.dto.AddressDto;
import alkz.mscurriculum.service.interfaces.IAddressService;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

  private final IProfessionalDetailsService detailsService;

  @Override
  public AddressDto.Response create(String detailId, AddressDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(detailId);
    if (Objects.nonNull(detail.getAddress())) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.ADDRESS_FOUND);
    }
    detail.setAddress(Address.build(request));
    return AddressDto.Response.build(detailsService.update(detail).getAddress());
  }

  @Override
  public AddressDto.Response update(String detailId, String id, AddressDto.Request request) {
    ProfessionalDetail detail =  detailsService.findById(detailId);
    if (Objects.isNull(detail.getAddress()) || !id.equals(detail.getAddress().getId())) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.ADDRESS_NOT_FOUND);
    }
    detail.getAddress().update(request);
    detailsService.update(detail);
    return AddressDto.Response.build(detail.getAddress());
  }

  @Override
  public void delete(String detailId, String id) {
    throw new CustomCommonException(HttpStatus.NOT_IMPLEMENTED, ECommonError.METHOD_NOT_IMPLEMENTED);
  }
}
