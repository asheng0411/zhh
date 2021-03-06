package cn.bdqn.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将/abc/**访问映射到classpath:/mystatic/
        registry.addResourceHandler("/abc/**").addResourceLocations("classpath:/mystatic/");
    }
}
