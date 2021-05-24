package cn.eros.dateformatdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 创建时间：2021/5/6 16:46
 * swagger文档配置文件
 *
 * @author wyb
 */
@Slf4j
@Configuration
@EnableSwagger2
//@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * SwaggerUI启用状态。默认：关闭
     */
    @Value("${swagger.enabled:true}")
    private boolean enable;

    /**
     * 注入API文档
     */
    @Bean
    public Docket createRestApi() {
        log.info("SwaggerUI 启用状态:{}", this.enable);

        return new Docket(DocumentationType.SWAGGER_2)
            .enable(this.enable)
            .directModelSubstitute(LocalTime.class, String.class)
            .apiInfo(this.getApiInfo("惠管理-接口API文档", "1.0"))
            .select()
            .paths(path -> path != null && path.startsWith("/")
                // 过滤以下路径
                && !path.startsWith("/test")
                && !path.startsWith("/error")
                && !path.startsWith("/basic")
            )
            .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 获取API文档信息
     *
     * @param title   API标题
     * @param version API版本号
     * @return API文档信息
     */
    @SuppressWarnings("SameParameterValue")
    private ApiInfo getApiInfo(String title, String version) {
        return new ApiInfoBuilder()
            .title(title)
            .description("")
            .version(version)
            .build();
    }
}
