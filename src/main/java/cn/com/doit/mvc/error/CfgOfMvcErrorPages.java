package cn.com.doit.mvc.error;


import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class CfgOfMvcErrorPages {
	@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer(){

			public void customize(ConfigurableEmbeddedServletContainer container) {
				 container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorPages/500.html"));
				 container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/errorPages/400.html"));
				 container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/errorPages/404.html"));
			} 	
        	
        };
    }

}
