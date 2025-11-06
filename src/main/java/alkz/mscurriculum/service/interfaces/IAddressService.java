package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.AddressDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface IAddressService extends IGenericCrudDetailService<AddressDto.Request, AddressDto.Response, String> {}
