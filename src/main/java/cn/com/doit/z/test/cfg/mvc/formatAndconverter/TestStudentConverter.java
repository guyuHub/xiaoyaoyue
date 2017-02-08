package cn.com.doit.z.test.cfg.mvc.formatAndconverter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import cn.com.doit.z.test.pojo.TestStudent;

public class TestStudentConverter implements HttpMessageConverter {

	public boolean canRead(Class clazz, MediaType mediaType) {
		return TestStudent.class.isAssignableFrom(clazz);
	}

	public boolean canWrite(Class clazz, MediaType mediaType) {
		// return TestStudent.class.isAssignableFrom(clazz);
		return false;
	}

	public List getSupportedMediaTypes() {
		List<MediaType> me = new ArrayList<MediaType>();
		me.add(MediaType.ALL);
		return me;
	}

	public Object read(Class clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		InputStream readOfTestStudent = inputMessage.getBody();
		String temp = StreamUtils.copyToString(readOfTestStudent,
				Charset.forName("UTF-8"));
		String last = URLDecoder.decode(temp, "UTF-8");
		System.out.println("===============" + last);
		// ===============address=黄河&name=是否5&testAge=45
		TestStudent toreturn = new TestStudent();
		String[] testStudent = last.split("&");
		for (String string : testStudent) {
			if (string.indexOf("address") != -1) {
				toreturn.setAdress(string.substring(string.indexOf("=") + 1,
						string.length()));
			} else if (string.indexOf("name") != -1) {
				toreturn.setName(string.substring(string.indexOf("=") + 1,
						string.length()));
			} else if (string.indexOf("testAge") != -1) {
				toreturn.setAge(Integer.parseInt(string.substring(
						string.indexOf("=") + 1, string.length())));
			}
		}
		return toreturn;
	}

	public void write(Object t, MediaType contentType,
			HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
	}

}
