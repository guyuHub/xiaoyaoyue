package cn.com.doit.mvc.view.pdf;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfStamperView;





public  class AlreadlyPdfView  {
	public static final String FILENAME = "mergePdfFileName";

//	@SuppressWarnings("unchecked")
//	@Override
//	protected void mergePdfDocument(Map<String, Object> model,
//			PdfStamper stamper, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		response.setHeader("Content-Disposition", "attachment;filename=" + new String(model.get(FILENAME).toString().getBytes(), "ISO8859-1"));
//		AcroFields fields = stamper.getAcroFields();
//		fillData(fields, (Map<String, String>) model.get(DATA));
//		stamper.setFormFlattening(true);
//	}

//	private void fillData(AcroFields fields, Map<String, String> data)
//			throws IOException, DocumentException {
//		for (String key : data.keySet()) {
//			String value = data.get(key);
//			fields.setField(key, value);
//		}
//	}
//
//	@Override
//	protected void mergePdfDocument(Map<String, Object> model,
//			PdfStamper stamper, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		// TODO Auto-generated method stub		
//	}
}
