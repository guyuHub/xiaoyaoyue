package cn.com.doit.pojo.main;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuNode implements Serializable,Comparable<MenuNode>{
	private String Id; // 菜单Id
	private String parentId; // 父菜单Id
	private String url; // 菜单url
	private String imageUrl; // 菜单图标url
	private String title; // 菜单名称
	private int brotherSeq; 
	private ArrayList<Object> Chirdlen;
	private int type; //0:非叶节点 1：叶子节点
	private int roleLeav;//菜单所属权限
	
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


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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


	public int getBrotherSeq() {
		return brotherSeq;
	}

	public void setBrotherSeq(int brotherSeq) {
		this.brotherSeq = brotherSeq;
	}

	public ArrayList getChirdlen() {
		return Chirdlen;
	}

	public void setChirdlen(ArrayList chirdlen) {
		Chirdlen = chirdlen;
	}

	@Override
	public String toString() {
		return "MenuNode [Id=" + Id + ", parentId=" + parentId + ", url=" + url
				+ ", imageUrl=" + imageUrl + ", title=" + title
				+ ", brotherSeq=" + brotherSeq + ", Chirdlen=" + show(Chirdlen)
				+ ", type=" + type + "]";
	}

	/**
	 * @param chirdlen2
	 * @return
	 */ 
	private String show(ArrayList<Object> chirdlen2) {
		String result="";
		if(chirdlen2==null){
			return "--";
		}
		for (Object object : chirdlen2) {	
			if(object instanceof MenuNode){
				result=result+((MenuNode)object).toString();
				 
			}
		} 
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(MenuNode o) {	
		return this.getBrotherSeq()-o.getBrotherSeq();
	}



}
