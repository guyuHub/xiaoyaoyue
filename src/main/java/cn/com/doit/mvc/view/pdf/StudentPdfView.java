package cn.com.doit.mvc.view.pdf;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import cn.com.doit.pojo.login.student_info;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class StudentPdfView extends AbstractPdf5View {
private Font   FontChinese ;
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Object> toShow = (List<Object>) model.get("whichsToShow");
		student_info arget = new student_info();
		String[] whichs = arget.whichFiledsToShow();
		PdfPTable table = new PdfPTable(whichs.length);
		 BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                 "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		    FontChinese = new Font(bfChinese, 14, Font.NORMAL);
		document.add(new Paragraph("测试", FontChinese));
		  PdfPCell cell = null;  
         //表头
		for (String string : whichs) {
			cell=new PdfPCell(new Paragraph(string, FontChinese));
			table.addCell(cell);
		}
		for (Object object : toShow) {
			arget = (student_info) object;
			doadd(arget, table);
		}
		document.add(table);

	}

	private void doadd(student_info arget, PdfPTable table) {
		PdfPCell cell=new PdfPCell(new Paragraph(arget.getNumber().toString(), FontChinese));
		cell.setBackgroundColor(BaseColor.CYAN);
		table.addCell(cell);
		PdfPCell cell2=new PdfPCell(new Paragraph(arget.getName(), FontChinese));
		cell2.setBackgroundColor(BaseColor.CYAN);
		table.addCell(new PdfPCell(cell2));
		PdfPCell cell3=new PdfPCell(new Paragraph(arget.getSex(), FontChinese));
		cell3.setBackgroundColor(BaseColor.CYAN);
		table.addCell(new PdfPCell(cell3));
		table.addCell(new PdfPCell(new Paragraph(arget.getAge().toString(), FontChinese)));
		table.addCell(new PdfPCell(new Paragraph(arget.getStuclass(), FontChinese)));
		table.addCell(new PdfPCell(new Paragraph(arget.getAddress(), FontChinese)));

	}



}
