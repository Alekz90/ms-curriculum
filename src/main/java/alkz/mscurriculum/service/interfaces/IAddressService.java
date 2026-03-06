package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.dto.AddressDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface IAddressService
    extends IGenericCrudDetailService<String, String, AddressDto.Request, AddressDto.Request, AddressDto.Response> {}
