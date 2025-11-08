package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.AddressDto;
import alkz.mscurriculum.model.LanguageDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface ILanguagesService
    extends IGenericCrudDetailService<String, String, LanguageDto.Request, LanguageDto.Response> {}
