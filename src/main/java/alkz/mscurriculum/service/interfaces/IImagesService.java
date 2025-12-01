package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.dto.ImageDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface IImagesService
    extends IGenericCrudDetailService<String, String, ImageDto.Request, ImageDto.Request, ImageDto.Response> {}
