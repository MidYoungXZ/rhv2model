package cn.demo.rhv2model.confirguration;

import cn.demo.rhv2model.service.RhModelService;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAutoConfiguration {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/rhv2model");
    }

    @Bean
    public RhModelService rhModelService() {
        return new RhModelService();
    }
}
