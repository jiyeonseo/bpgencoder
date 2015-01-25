package bpgencoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import java.util.*;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BpgencoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BpgencoderApplication.class, args);
    }

    @Bean(name = "velocityConfig")
    public VelocityConfigurer velocityConfig() {
        VelocityConfigurer config = new VelocityConfigurer();
        config.setResourceLoaderPath("/WEB-INF/views/");

        Map<String, Object> velocityPropertiesMap = new HashMap<String, Object>();
        velocityPropertiesMap.put("input.encoding", "UTF-8");
        velocityPropertiesMap.put("output.encoding", "UTF-8");
        velocityPropertiesMap.put("velocimacro.library.autoreload", false);
        velocityPropertiesMap.put("directive.foreach.counter.initial.value", 0);
        velocityPropertiesMap.put("velocimacro.library.autoreload", false);
        config.setVelocityPropertiesMap(velocityPropertiesMap);
        return config;
    }

    @Bean(name="multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5*1024*1024);
        return multipartResolver;
    }

    @Bean
    public ContentNegotiatingViewResolver resolver() {
        Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>();
        mediaTypes.put("html", MediaType.TEXT_HTML);
        mediaTypes.put("json", MediaType.APPLICATION_JSON);

        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setOrder(1);
        resolver.setContentNegotiationManager(
                new ContentNegotiationManager(
                        new PathExtensionContentNegotiationStrategy(mediaTypes)
                )
        );
        resolver.setDefaultViews(defaultViews());
        resolver.setViewResolvers(viewResolvers());
        return resolver;
    }

    private List<View> defaultViews() {
        List<View> list = new ArrayList<View>();
        list.add(new MappingJackson2JsonView());
        return list;
    }

    private List<ViewResolver> viewResolvers() {
        VelocityViewResolver viewResolver = new VelocityViewResolver();
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setSuffix(".html");
        viewResolver.setRedirectHttp10Compatible(false);
        List<ViewResolver> list = new ArrayList<ViewResolver>();
        list.add(viewResolver);
        return list;
    }

}
