package com.dus.dusframework.web.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.dus.dusframework"})
@ImportResource(locations={"classpath:spring-mybatis.xml"})
public class DusWebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    public DusWebConfig() {
        super();
    }
    
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = arg0;
	} 
	
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/static/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/static/dist/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/static/dist/js/");
        registry.addResourceHandler("/vendor/**")
    		.addResourceLocations("/WEB-INF/static/vendor/");
        registry.addResourceHandler("/data/**")
    		.addResourceLocations("/WEB-INF/static/data/");
        registry.addResourceHandler("/pages/**")
    		.addResourceLocations("/WEB-INF/static/pages/");
        registry.addResourceHandler("/dist/**")
    		.addResourceLocations("/WEB-INF/static/dist/");
        /**
        registry.addResourceHandler("/vendor/bootstrap/css/**")
        	.addResourceLocations("/WEB-INF/static/vendor/bootstrap/css/");
        registry.addResourceHandler("/vendor/bootstrap/js/**")
    	.addResourceLocations("/WEB-INF/static/vendor/bootstrap/js/");
        registry.addResourceHandler("/vendor/bootstrap/fonts/**")
    	.addResourceLocations("/WEB-INF/static/vendor/bootstrap/fonts/");
        
        registry.addResourceHandler("/vendor/morrisjs/**")
    	.addResourceLocations("/WEB-INF/static/vendor/morrisjs/");
        
        registry.addResourceHandler("/vendor/metisMenu/**")
    	.addResourceLocations("/WEB-INF/static/vendor/metisMenu/");
        
        registry.addResourceHandler("/vendor/font-awesome/**")
    	.addResourceLocations("/WEB-INF/static/vendor/font-awesome/"); **/
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        // SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(true);
        // 设置字符编码，解决中文乱码
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        // SpringTemplateEngine automatically applies SpringStandardDialect and
        // enables Spring's own MessageSource message resolution mechanisms.
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
        // speed up execution in most scenarios, but might be incompatible
        // with specific cases when expressions in one template are reused
        // across different data types, so this flag is "false" by default
        // for safer backwards compatibility.
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        
        // 设置字符编码，解决中文乱码
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
    
    /**
     * 支持json 格式的报文转换 
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    	return new MappingJackson2HttpMessageConverter();
    }
    
    /**
     * 中文乱码，将iso-8859 转换为 utf-8  
     * @return
     */
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
    	StringHttpMessageConverter convert = new StringHttpMessageConverter(Charset.forName("UTF-8"));
    	//convert.DEFAULT_CHARSET
    	return convert;
    }
    
    /**
     * 解决中文乱码 ， 使用 StringHttpMessageConverter(Charset.forName("UTF-8"));
     * 支持json格式报文转换；
     */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(this.stringHttpMessageConverter());
		converters.add(this.mappingJackson2HttpMessageConverter());
	}
	
	
}
