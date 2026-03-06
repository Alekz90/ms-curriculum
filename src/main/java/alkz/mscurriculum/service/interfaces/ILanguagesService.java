package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.dto.LanguageDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface ILanguagesService
    extends IGenericCrudDetailService<String, String, LanguageDto.Request, LanguageDto.Request, LanguageDto.Response> {}
