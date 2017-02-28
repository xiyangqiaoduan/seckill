package com.yangcb.seckill.dto;


/***
 * 树形菜单信息
 * @author yangcb
 *
 */
public class Tree {

	private String id;
	private String text;
	private String iconCls;
	private String url;
	private String state;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Tree [id=" + id + ", text=" + text + ", iconCls=" + iconCls + ", url=" + url + ", state=" + state + "]";
	}
	
	
	
	
}
