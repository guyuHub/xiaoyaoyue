/**
 * 
 */
package cn.com.doit.pojo.book;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class Free_book_info implements Serializable {

	private String book_name;   // 书名
	private String book_id;     // 书籍编号
	private String book_author; // 书籍作者
	private Date start_time;    // 书籍时间
	private Date ent_time;      // 最后更新时间
	private int state;          // 书籍状态 0:新书上传 1：连载 2:完本
	private String book_cover_url;// 书籍封面图片地址
	private long number;        // 书籍字数
//点击数，推荐数动态时间管理，初步考虑使用类似Timeitems对象	

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnt_time() {
		return ent_time;
	}

	public void setEnt_time(Date ent_time) {
		this.ent_time = ent_time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getBook_cover_url() {
		return book_cover_url;
	}

	public void setBook_cover_url(String book_cover_url) {
		this.book_cover_url = book_cover_url;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

}
