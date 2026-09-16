package alkz.mscurriculum.security;

import akz.commonutils.config.SwaggerConfiguration;
import akz.commonutils.util.SwaggerProperties;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public SwaggerProperties swaggerProperties() {
    return new SwaggerProperties();
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new SwaggerConfiguration(swaggerProperties()).customOpenAPI();
  }

  @Bean
  public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
    return new MongoTransactionManager(dbFactory);
  }
}
