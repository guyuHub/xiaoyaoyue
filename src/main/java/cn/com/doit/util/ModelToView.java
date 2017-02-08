package cn.com.doit.util;

import java.util.HashMap;
import java.util.List;

public class ModelToView {
	private static String name = "modelToview";
	private String[] filedsToShow;//��չʾ���ֶμ���
	private List<Object>  objects;



	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		ModelToView.name = name;
	}

	public String[] getFiledsToShow() {
		return filedsToShow;
	}

	public void setFiledsToShow(String[] filedsToShow) {
		this.filedsToShow = filedsToShow;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}



}
