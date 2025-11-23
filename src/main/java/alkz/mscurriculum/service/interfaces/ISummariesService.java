package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.dto.SummaryDto;
import alkz.mscurriculum.service.interfaces.generic.IGenericCrudDetailService;

public interface ISummariesService
    extends IGenericCrudDetailService<String, String, SummaryDto.Request, SummaryDto.Request, SummaryDto.Response> {}
