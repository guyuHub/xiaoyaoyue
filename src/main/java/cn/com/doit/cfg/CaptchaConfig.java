package cn.com.doit.cfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityProperties;
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
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

import cn.com.doit.captcha.provider.xvcode.xvcode.generator.GifVCGenerator;
import cn.com.doit.captcha.provider.xvcode.xvcode.generator.PngVCGenerator;
import cn.com.doit.mvc.formatAndconverter.Converter_StringToDate;
import cn.com.doit.mvc.formatAndconverter.Formatter_StringToDate;
import cn.com.doit.util.ApplicationFactoryUtil;
import cn.com.doit.z.test.cfg.mvc.formatAndconverter.TestStudentConverter;
@SuppressWarnings({ "unused", "deprecation" })
@Configuration
public class CaptchaConfig{
	@Bean(name="pngVCGenerator")
	public PngVCGenerator pngVCGenerator(){
		return new PngVCGenerator();
	};
	@Bean(name="gifVCGenerator")
	public GifVCGenerator gifVCGenerator(){
		return new GifVCGenerator();
	}
}
