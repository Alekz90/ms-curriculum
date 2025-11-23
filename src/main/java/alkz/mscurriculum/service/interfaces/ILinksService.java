package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.LinkDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface ILinksService
    extends IGenericCrudDetailService<String, String, LinkDto.Request, LinkDto.Request, LinkDto.Response> {}
