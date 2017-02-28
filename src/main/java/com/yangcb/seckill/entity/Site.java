package com.yangcb.seckill.entity;
/**
 * 站点信息
 * @author yangcb
 *
 */
public class Site {

	private  int  siteId;  //站点ID
	private String siteName;//站点名称
	private String siteUrl;//站点地址
	private String siteLogo;//站点logo
	private String siteKeyword;//站点关键字
	private String siteDescription;//站点描述
	private String siteCopyright;//站点版权信息
	private String siteStyle;//站点模板样式
	private String siteMobileStyle;//手机样式
	private String siteMobileState;	//手机启用状态
	
	
	@Override
	public String toString() {
		return "Site [siteId=" + siteId + ", siteName=" + siteName + ", siteUrl=" + siteUrl + ", siteLogo=" + siteLogo
				+ ", siteKeyword=" + siteKeyword + ", siteDescription=" + siteDescription + ", siteCopyright="
				+ siteCopyright + ", siteStyle=" + siteStyle + ", siteMobileStyle=" + siteMobileStyle
				+ ", siteMobileState=" + siteMobileState + "]";
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public String getSiteLogo() {
		return siteLogo;
	}
	public void setSiteLogo(String siteLogo) {
		this.siteLogo = siteLogo;
	}
	public String getSiteKeyword() {
		return siteKeyword;
	}
	public void setSiteKeyword(String siteKeyword) {
		this.siteKeyword = siteKeyword;
	}
	public String getSiteDescription() {
		return siteDescription;
	}
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}
	public String getSiteCopyright() {
		return siteCopyright;
	}
	public void setSiteCopyright(String siteCopyright) {
		this.siteCopyright = siteCopyright;
	}
	public String getSiteStyle() {
		return siteStyle;
	}
	public void setSiteStyle(String siteStyle) {
		this.siteStyle = siteStyle;
	}
	public String getSiteMobileStyle() {
		return siteMobileStyle;
	}
	public void setSiteMobileStyle(String siteMobileStyle) {
		this.siteMobileStyle = siteMobileStyle;
	}
	public String getSiteMobileState() {
		return siteMobileState;
	}
	public void setSiteMobileState(String siteMobileState) {
		this.siteMobileState = siteMobileState;
	}
	
	
	
	
	
	
}
