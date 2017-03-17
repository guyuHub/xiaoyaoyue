package cn.com.doit.pojo.main;

import java.io.Serializable;

public class MenuNode implements Serializable {
	private String Id; // 菜单Id
	private String parentId; // 父菜单Id
	private String leftId; // 兄菜单Id
	private String rightId; // 第菜单Id
	private String url; // 菜单url
	private String imageUrl; // 菜单图标url
	private String title; // 菜单名称
	private String firstChirdlen; //第一个子菜单Id
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLeftId() {
		return leftId;
	}

	public void setLeftId(String leftId) {
		this.leftId = leftId;
	}

	public String getRightId() {
		return rightId;
	}

	public void setRightId(String rightId) {
		this.rightId = rightId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstChirdlen() {
		return firstChirdlen;
	}

	public void setFirstChirdlen(String firstChirdlen) {
		this.firstChirdlen = firstChirdlen;
	}


}
