package cn.com.doit.mvc.viewResolver;

import java.util.HashMap;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import cn.com.doit.mvc.view.pdf.AbstractPdf5View;
import cn.com.doit.mvc.view.pdf.StudentPdfView;

@Component("PdfViewResolver")
public class PdfViewResolver implements ViewResolver {
	private static HashMap<String, AbstractPdf5View> keyToValue = new HashMap<String, AbstractPdf5View>();

	@PostConstruct
	private static void toinit() {
		keyToValue.put("student", new StudentPdfView());
	}

	public View resolveViewName(String viewName, Locale locale)
			throws Exception {

		return (StudentPdfView) keyToValue.get("student");

	}

}
