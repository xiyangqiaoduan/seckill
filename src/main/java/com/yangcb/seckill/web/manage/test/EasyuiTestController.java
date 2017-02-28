package com.yangcb.seckill.web.manage.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.yangcb.seckill.servlet.WebSocketMessageServlet;
import com.yangcb.seckill.websocket.WebsocketEndPoint;

@Controller
@RequestMapping("/test")
public class EasyuiTestController {

	@RequestMapping("/index")
	public String test01() {
		return "manage/admin/easyui";
	}

	@RequestMapping(value = "/login", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, String> test02(@RequestParam(defaultValue = "你好") String user,
			@RequestParam(defaultValue = "111111") String pwd, HttpSession httpSession) {
		Map<String, String> map = new HashMap<String, String>();

		if (user.equals("admin")) {
			map.put("success", "true");
			map.put("msg", "登录成功");
		} else {
			map.put("success", "false");
			map.put("msg", "用户名或者密码错误");
		}
		map.put("user", user);
		map.put("pwd", pwd);
		map.put("ip", "192.168.1.200");

		httpSession.setAttribute("user", user);
		httpSession.setAttribute("IP", "192.168.1.200");
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("/system_login")
	public String login() {

		return "manage/admin/login";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "manage/admin/admin";
	}

	@RequestMapping(value = "/nav", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public List<Map<String, Object>> nav(@RequestParam(defaultValue = "0") int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (id == 0) {
			// 树形菜单选项

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			map.put("text", "权限管理");
			map.put("iconCls", "icon-system");
			map.put("url", "");
			map.put("state", "closed");

			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("id", 3);
			map2.put("text", "消息管理");
			map2.put("iconCls", "icon-user");
			map2.put("url", "");
			map2.put("nid", 0);
			map2.put("state", "closed");

			list.add(map);
			list.add(map2);

		} else if (id == 1) {
			Map<String, Object> map11 = new HashMap<String, Object>();
			map11.put("id", 2);
			map11.put("text", "管理员管理");
			map11.put("iconCls", "icon-manager");
			map11.put("url", "/test/manager");
			map11.put("state", "open");
			list.add(map11);
		} else if (id == 3) {
			Map<String, Object> map21 = new HashMap<String, Object>();
			map21.put("id", 4);
			map21.put("text", "通知管理");
			map21.put("state", "open");
			map21.put("iconCls", "icon-group");
			map21.put("url", "/test/notice");
			map21.put("nid", 3);
			list.add(map21);
		}
		// map.put("children", children);

		List<Map<String, Object>> children2 = new ArrayList<Map<String, Object>>();

		// map2.put("children", children2);

		return list;
	}

	@RequestMapping("/manager")
	public String manager() {
		return "manage/admin/manager";
	}

	@RequestMapping(value = "/manager_data", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, Object> managerData(Page page, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();

		User user2 = (User) session.getAttribute("myUser");

		List<User> users = new ArrayList<User>();
		if (user2 != null) {
			users.add(user2);
		}
		for (int i = page.getRows() * (page.getPage() - 1) + 1; i <= page.getRows() * page.getPage(); i++) {
			User user = new User(i, "admin_" + i, i % 2 > 0 ? "超级管理员" : "维护人员", "2015-06-" + (i < 10 ? "0" + i : i));
			users.add(user);
		}
		map.put("rows", users);
		map.put("total", 100);
		return map;
	}

	@RequestMapping(value = "/add_manager", produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public Map<String, Object> addManager(User user, String authName, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("msg", "新增用户成功！");

		// System.out.println(authName+" ID标示"+authId);

		User user2 = new User(101, "杨春报", "超级管理员、维护人员", "2016-07-01");
		session.setAttribute("myUser", user2);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	@RequestMapping(value = "/getmanager", produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public User getmanager() {
		// Map<String, Object> map=new HashMap<String,Object>();
		User user = new User(1, "杨春报", "管理员管理,会员管理", "2016-06-18");

		return user;
	}

	@RequestMapping(value = "/deleteManager", produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public Map<String, Object> deleteManager(@RequestParam(value = "ids[]") int[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(ids.length);

		map.put("num", ids.length);

		return map;
	}

	@RequestMapping("/test/send")
	public void sendMessage() {
		WebSocketMessageServlet.sendAll("测试", "你好");
	}

	@RequestMapping("/notice")
	public String notice1(){
		return "manage/admin/notice";
	}
	
	@RequestMapping("/getnotice")
	@ResponseBody
	public Map<String, Object> notice(String title,String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(StringUtils.isNotBlank(title)&&StringUtils.isNotBlank(content)){
			WebSocketMessageServlet.sendAll(title, content);
		}
		
		
		
		return map;

	}
	@RequestMapping("/websocket")
	public String websocket(){
		return "manage/admin/websocket";
	}
	
	@Autowired
	WebsocketEndPoint websocket;
	
	@RequestMapping("/sendWebSocket")
	@ResponseBody
	public Map<String, Object> sendWebSocket(){
		Map<String, Object> map=new HashMap<String,Object>();
		websocket.sendMessageToUsers(new TextMessage("呵呵，收到了"));
		map.put("code", 0000);
		map.put("msg", "success");
		return  map;
	}
	
	
	

}

class Page {
	private int page;
	private int rows;
	private String sort;
	private String order;

	@Override
	public String toString() {
		return "Page [page=" + page + ", rows=" + rows + ", sort=" + sort + ", order=" + order + "]";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}

class User {

	private int id;
	private String pwd;
	private String user;
	private String auth;
	private String date;

	public User(int id, String user, String auth, String date) {
		super();
		this.id = id;
		this.user = user;
		this.auth = auth;
		this.date = date;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
