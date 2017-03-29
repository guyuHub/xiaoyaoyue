package cn.com.doit.main.service.impl;

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

import cn.com.doit.login.service.LoginService;
import cn.com.doit.main.service.MainService;
import cn.com.doit.pojo.book.Free_book_info;
import cn.com.doit.pojo.login.user_info;
import cn.com.doit.pojo.main.MenuNode;
import cn.com.doit.util.JiaMiUtil;
import cn.com.doit.util.asReadPolicy;
import cn.com.doit.util.cacheService;

@Configuration(value = "mainService")
public class MainServiceImpl implements MainService {
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
				if(!menuNode.getId().equals("100017")){
					root.add(menuNode);			
				}
				
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
		MenuNodes.add(xx);

		MenuNodes.add(wx);
		MenuNodes.add(qh);
		MenuNodes.add(xh);
	  return MenuNodes;
	}
	/* (non-Javadoc)
	 * @see cn.com.doit.main.service.MainService#getCarouselBooks()
	 */
	public List<Free_book_info> getCarouselBooks(String key) {
	      Record var=cache.read(asReadPolicy.newInstansce().setNamespace("freeRead").setSetname("circleRecommend").setKey(key));
		return null;
	}
}
