package cn.com.doit.mvc.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.apache.catalina.security.SecurityUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Operation;
import com.aerospike.client.Record;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;

import cn.com.doit.mvc.service.MainService;
import cn.com.doit.mvc.service.LoginService;
import cn.com.doit.pojo.book.Free_book_info;
import cn.com.doit.pojo.login.user_info;
import cn.com.doit.pojo.main.MenuNode;
import cn.com.doit.util.JiaMiUtil;
import cn.com.doit.util.asReadPolicy;
import cn.com.doit.util.asWritePolicy;
import cn.com.doit.util.cacheService;

@Configuration(value = "mainService")
public class MainServiceImpl implements MainService {
	@Value(value="${baselUrl}")
	private String baseUrl;
 @Resource(name="cache")
  private cacheService cache;
	public List<MenuNode> showMenus(user_info user) {
		List<MenuNode> MenuNodes = processMenuNodes(createMenus());
		return MenuNodes;
	}
	public List<MenuNode> showCloumMenus(user_info user) {
		List<MenuNode> MenuNodes = processMenuNodes(createCloumMenus());
		return MenuNodes;
	}
	@Test
	public void test() {
		List<MenuNode> root = processMenuNodes(createCloumMenus());
		for (MenuNode menuNode : root) {
			System.out.println(menuNode.toString());
		}
	}

	private List<MenuNode> processMenuNodes(List<MenuNode> MenuNodes) {
		List<MenuNode> root = new ArrayList<MenuNode>();
		Map<String, MenuNode> allMap = new HashMap<String, MenuNode>();
		for (MenuNode menuNode : MenuNodes) {
			allMap.put(menuNode.getId(), menuNode);
		}
		for (MenuNode menuNode : MenuNodes) {
				buildParentTree(menuNode, allMap);		
					root.add(menuNode);			
				
		}
		allMap = null;
		Collections.sort(root);
		return root;
	}

	/**
	 * @param menuNode
	 * @param allMap
	 */
	private void buildParentTree(MenuNode menuNode, Map allMap) {
	try {
		MenuNode parent = (MenuNode) allMap.get(menuNode.getParentId());
		ArrayList<MenuNode> ch;
		if(parent!=null){
			if (parent.getChirdlen() == null) {
				ch = new ArrayList<MenuNode>();
			} else {
				ch = parent.getChirdlen();
			}
			ch.add(menuNode);
			Collections.sort(ch);
			parent.setChirdlen(ch);
		}
	
	} catch (Exception e) {
		System.out.println(menuNode.getId()+"-----------"+menuNode.getParentId());
	}

	}

	private List<MenuNode> createMenus() {
		List<MenuNode> MenuNodes = new ArrayList<MenuNode>();
		MenuNode zpfl = new MenuNode();
		zpfl.setId("100001");
		zpfl.setParentId(null);
		zpfl.setBrotherSeq(1);
		zpfl.setImageUrl(null);
		zpfl.setType(0);
		zpfl.setTitle("作品分类");

		MenuNode qbzp = new MenuNode();
		qbzp.setId("100002");
		qbzp.setParentId(null);
		qbzp.setBrotherSeq(2);
		qbzp.setImageUrl(null);
		qbzp.setType(0);
		qbzp.setTitle("全部作品");

		MenuNode ph = new MenuNode();
		ph.setId("100003");
		ph.setParentId(null);
		ph.setBrotherSeq(3);
		ph.setImageUrl(null);
		ph.setType(0);
		ph.setTitle("排行");

		MenuNode wb = new MenuNode();
		wb.setId("100004");
		wb.setParentId(null);
		wb.setBrotherSeq(4);
		wb.setImageUrl(null);
		wb.setType(0);
		wb.setTitle("完本");

		MenuNode mf = new MenuNode();
		mf.setId("100005");
		mf.setParentId(null);
		mf.setBrotherSeq(5);
		mf.setImageUrl(null);
		mf.setType(0);
		mf.setTitle("免费");

		MenuNode zjzq = new MenuNode();
		zjzq.setId("100006");
		zjzq.setParentId(null);
		zjzq.setBrotherSeq(6);
		zjzq.setImageUrl(null);
		zjzq.setType(0);
		zjzq.setTitle("作家专区");
		MenuNodes.add(zpfl);
		MenuNodes.add(qbzp);
		MenuNodes.add(ph);
		MenuNodes.add(wb);
		MenuNodes.add(mf);
		MenuNodes.add(zjzq);
		return MenuNodes;
	}
	public  List<MenuNode> createCloumMenus(){
		List<MenuNode> MenuNodes = new ArrayList<MenuNode>();
		MenuNode xh = new MenuNode();
		xh.setId("100007");
		xh.setParentId("100001");
		xh.setBrotherSeq(7);
		xh.setImageUrl(null);
		xh.setType(0);
		xh.setTitle("玄幻");

		MenuNode qh = new MenuNode();
		qh.setId("100008");
		qh.setParentId("100001");
		qh.setBrotherSeq(8);
		qh.setImageUrl(null);
		qh.setType(1);
		qh.setTitle("奇幻");
		MenuNode wx = new MenuNode();
		wx.setId("100009");
		wx.setParentId("100001");
		wx.setBrotherSeq(9);
		wx.setImageUrl(null);
		wx.setType(1);
		wx.setTitle("武侠");
		MenuNode xx = new MenuNode();
		xx.setId("100010");
		xx.setParentId("100001");
		xx.setBrotherSeq(10);
		xx.setImageUrl(null);
		xx.setType(1);
		xx.setTitle("仙侠");
		MenuNode ds = new MenuNode();
		ds.setId("100011");
		ds.setParentId("100001");
		ds.setBrotherSeq(11);
		ds.setImageUrl(null);
		ds.setType(1);
		ds.setTitle("都市");
		MenuNode zc = new MenuNode();
		zc.setId("100012");
		zc.setParentId("100001");
		zc.setBrotherSeq(12);
		zc.setImageUrl(null);
		zc.setType(1);
		zc.setTitle("职场");
		MenuNode js = new MenuNode();
		js.setId("100013");
		js.setParentId("100001");
		js.setBrotherSeq(13);
		js.setImageUrl(null);
		js.setType(1);
		js.setTitle("军事");
		MenuNode ls = new MenuNode();
		ls.setId("100014");
		ls.setParentId("100001");
		ls.setBrotherSeq(14);
		ls.setImageUrl(null);
		ls.setType(1);
		ls.setTitle("历史");
		MenuNode yx = new MenuNode();
		yx.setId("100015");
		yx.setParentId("100001");
		yx.setBrotherSeq(15);
		yx.setImageUrl(null);
		yx.setType(1);
		yx.setTitle("游戏");
		MenuNode ty = new MenuNode();
		ty.setId("100016");
		ty.setParentId("100001");
		ty.setBrotherSeq(16);
		ty.setImageUrl(null);
		ty.setType(1);
		ty.setTitle("体育");
		MenuNode kh = new MenuNode();
		kh.setId("100017");
		kh.setParentId("100001");
		kh.setBrotherSeq(17);
		kh.setImageUrl(null);
		kh.setType(1);
		kh.setTitle("科幻");
		MenuNode ly = new MenuNode();
		ly.setId("100018");
		ly.setParentId("100001");
		ly.setBrotherSeq(18);
		ly.setImageUrl(null);
		ly.setType(1);
		ly.setTitle("灵异");
		MenuNode ns = new MenuNode();
		ns.setId("100019");
		ns.setParentId("100001");
		ns.setBrotherSeq(19);
		ns.setImageUrl(null);
		ns.setType(1);
		ns.setTitle("女士");
		MenuNode ecy = new MenuNode();
		ecy.setId("100020");
		ecy.setParentId("100001");
		ecy.setBrotherSeq(20);
		ecy.setImageUrl(null);
		ecy.setType(1);
		ecy.setTitle("二次元");
		MenuNodes.add(ns);
		MenuNodes.add(ecy);
		MenuNodes.add(ly);
		MenuNodes.add(kh);
		MenuNodes.add(ty);
		MenuNodes.add(yx);
		MenuNodes.add(ls);
		MenuNodes.add(js);
		MenuNodes.add(zc);
		MenuNodes.add(ds);
		MenuNodes.add(xx);
		MenuNodes.add(wx);
		MenuNodes.add(qh);
		MenuNodes.add(xh);
	  return MenuNodes;
	}

	
	public List<Free_book_info> getCarouselBooks(String key) {
		 List<Free_book_info> result=null;
	      Record var=cache.read(asReadPolicy.newInstansce().setNamespace("freeRead").setSetname("circleRecommend").setKey(key).put("value"));
		 if(var==null){
			 System.out.println("-----no cache-------------");
			result=serchFromSql();
			cache.write(asWritePolicy.newInstansce().setNamespace("freeRead").setSetname("circleRecommend").setKey(key).put("value", result));
			 return result;
		 }
		 result=convert(var);	 
	      return result;
	}
	/**
	 * @param var
	 * @return
	 */
	private List<Free_book_info> convert(Record var) {
        Object vars=var.getValue("value");
		return (List<Free_book_info> )vars;
	}

	
	private List<Free_book_info> serchFromSql() {
		System.out.println(baseUrl);
		//查询需要在轮播插件推荐的书籍信息
		Free_book_info one =new Free_book_info();
		one.setBook_author("张三");
		one.setBook_cover_url(baseUrl+"resources/images/carousel/id1.jpg");
		one.setBook_id("1001");
		one.setBook_name("风戽");
		
		Free_book_info two =new Free_book_info();
		two.setBook_author("李四");
		two.setBook_cover_url(baseUrl+"resources/images/carousel/id2.jpg");
		two.setBook_id("1002");
		two.setBook_name("玉祁");
		
		Free_book_info three =new Free_book_info();
		three.setBook_author("王五");
		three.setBook_cover_url(baseUrl+"resources/images/carousel/id3.jpg");
		three.setBook_id("1003");
		three.setBook_name("泷灀");
		List<Free_book_info> list=new ArrayList<Free_book_info>();
		list.add(three);
		list.add(one);
		list.add(two);
		return list;
	}
}
