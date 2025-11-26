package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alejdaf.commonutils.util.enums.ECommonError;
import alkz.mscurriculum.document.Address;
import alkz.mscurriculum.document.Profile;
import alkz.mscurriculum.dto.AddressDto;
import alkz.mscurriculum.service.interfaces.IAddressService;
import alkz.mscurriculum.service.interfaces.IProfilesService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

  private final IProfilesService profilesService;

  @Override
  public AddressDto.Response create(String profileId, AddressDto.Request request) {
    Profile profile =  profilesService.findById(profileId);

    if (Objects.nonNull(profile.getAddress())) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.ADDRESS_FOUND);
    }
    profile.setAddress(Address.build(request));
    return AddressDto.Response.build(profilesService.update(profile).getAddress());
  }

  @Override
  public AddressDto.Response update(String profileId, String id, AddressDto.Request request) {
    Profile profile =  profilesService.findById(profileId);
    if (Objects.isNull(profile.getAddress()) || !id.equals(profile.getAddress().getId())) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.ADDRESS_NOT_FOUND);
    }
    profile.getAddress().update(request);
    profilesService.update(profile);
    return AddressDto.Response.build(profile.getAddress());
  }

  @Override
  public void delete(String profileId, String id) {
    throw new CustomCommonException(HttpStatus.NOT_IMPLEMENTED, ECommonError.METHOD_NOT_IMPLEMENTED);
  }
}
