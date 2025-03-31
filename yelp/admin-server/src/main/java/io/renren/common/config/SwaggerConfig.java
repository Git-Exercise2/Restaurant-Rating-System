

package io.renren.common.config;

import io.renren.common.constant.Constant;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger configuration
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI createRestApi() {
        return new OpenAPI()
                .info(apiInfo())
                .security(security());
    }

    private Info apiInfo() {
        return new Info()
                .title("Backend management")
                .description("document")
                .version("5.x");
    }

    private List<SecurityRequirement> security() {
        SecurityRequirement key = new SecurityRequirement();
        key.addList(Constant.TOKEN_HEADER, Constant.TOKEN_HEADER);

        List<SecurityRequirement> list = new ArrayList<>();
        list.add(key);
        return list;
    }
}
