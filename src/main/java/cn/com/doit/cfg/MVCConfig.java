package cn.com.doit.cfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import cn.com.doit.mvc.formatAndconverter.Converter_StringToDate;
import cn.com.doit.mvc.formatAndconverter.Formatter_StringToDate;
import cn.com.doit.util.ApplicationFactoryUtil;
import cn.com.doit.z.test.cfg.mvc.formatAndconverter.TestStudentConverter;
@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter{
	
	/*WebMvcConfigurerAdapter的实现原理：见WebMvcAutoConfiguration-->DelegatingWebMvcConfiguration的
	 * private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();
	 * ---->setConfigurers
	 * 简而言之，其会遍历容器中所有实现WebMvcConfigurerAdapter的类的bean，执行相应方法*/
	
	@Autowired
	@Qualifier("applicationFactoryUtil")
	private ApplicationFactoryUtil applicationFactoryUtil;
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
	
		registry.addConverter(new Converter_StringToDate());
		registry.addFormatter(new Formatter_StringToDate());
	}

//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//	}


	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		TestStudentConverter conv=new TestStudentConverter();
		converters.add(conv);
	}

//	@Override
//	public Validator getValidator() {
//		return null;
//	}

	/**
	 * WebMvcConfigurationSupport ContentNegotiationManager	
	 *对于pdf后缀名来说不需要，因为ContentNegotiationManager的resolveMediaTypes方法会调用的其注册的策略类的父类AbstractMappingContentNegotiationStrategy和子类相关方法例如lookupMediaType和handleMatch添加一些路径和MimeType的映射关系，这些路径需要是ServletContext里的MimeType的组成部分*/
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//如果不加该注释，则请求中应指定content-type为application/json，若不指定，则mvc会提取acceptl来设置MimeType
		//或者在请求路径中以.json结尾，会经过处理成为MimeType--application/json，详情见源码
//		configurer.ignoreAcceptHeader(true).defaultContentType(
//                MediaType.TEXT_HTML);
//		MimeType type=MimeTypeUtils.parseMimeType("application/pdf");
//		  configurer.mediaType("pdf",
//		new MediaType(type.getType(), type.getSubtype(), type.getParameters()));
		  //见https://my.oschina.net/heweipo/blog/340040
//		  configurer.favorPathExtension(true).
//		     favorParameter(true).
//		     ignoreAcceptHeader(false).
//		     useJaf(false).
//		     defaultContentType(MediaType.TEXT_HTML).
//		     mediaType("json", MediaType.APPLICATION_JSON);
				
	}
	
//	@Override
//	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//	}
//
//
//	@Override
//	public void configurePathMatch(PathMatchConfigurer configurer) {
//	}
//
//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//	}
//
//	@Override
//	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
//	}
//
//	@Override
//	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//	}
//
//	@Override
//	public MessageCodesResolver getMessageCodesResolver() {
//		return null;
//	}
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//	}
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//	}
//
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//	}
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	}
//
//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//	}
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//	}
//	// 鏂囦欢涓婁紶璁剧疆
//    @Bean
//    public MultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(1000000);
//        return multipartResolver;
//    }

	public ApplicationFactoryUtil getApplicationFactoryUtil() {
		return applicationFactoryUtil;
	}

	public void setApplicationFactoryUtil(
			ApplicationFactoryUtil applicationFactoryUtil) {
		this.applicationFactoryUtil = applicationFactoryUtil;
	}
	
}
