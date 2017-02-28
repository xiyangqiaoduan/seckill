package com.yangcb.seckill.entity;

import java.util.Date;

/**
 * 类型
 * @author yangcb
 *
 */
public class Category {

	private  int categoryId;//栏目类型ID
	private String categoryTitle;//栏目标题
	private int categorySort; //栏目排序
	private Date categoryDateTime;//创建时间
	
	private int categoryMangerId;//创建人标示
	private int categoryModelId;//所属模块id
	private String categorySmallimg;//类型图标
	private int categoryAppid;//所属应用id
	private String categoryDescription;//类型栏目描述
	
	
	
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categorySort="
				+ categorySort + ", categoryDateTime=" + categoryDateTime + ", categoryMangerId=" + categoryMangerId
				+ ", categoryModelId=" + categoryModelId + ", categorySmallimg=" + categorySmallimg + ", categoryAppid="
				+ categoryAppid + ", categoryDescription=" + categoryDescription + "]";
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public int getCategorySort() {
		return categorySort;
	}
	public void setCategorySort(int categorySort) {
		this.categorySort = categorySort;
	}
	public Date getCategoryDateTime() {
		return categoryDateTime;
	}
	public void setCategoryDateTime(Date categoryDateTime) {
		this.categoryDateTime = categoryDateTime;
	}
	public int getCategoryMangerId() {
		return categoryMangerId;
	}
	public void setCategoryMangerId(int categoryMangerId) {
		this.categoryMangerId = categoryMangerId;
	}
	public int getCategoryModelId() {
		return categoryModelId;
	}
	public void setCategoryModelId(int categoryModelId) {
		this.categoryModelId = categoryModelId;
	}
	public String getCategorySmallimg() {
		return categorySmallimg;
	}
	public void setCategorySmallimg(String categorySmallimg) {
		this.categorySmallimg = categorySmallimg;
	}
	public int getCategoryAppid() {
		return categoryAppid;
	}
	public void setCategoryAppid(int categoryAppid) {
		this.categoryAppid = categoryAppid;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	
	
}
