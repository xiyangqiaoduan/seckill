package com.yangcb.seckill.enums;

public enum ResourceStatEnum {

	menu(0, "菜单"), action(1, "地址");

	private int state;
	private String stateInfo;

	private ResourceStatEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public static ResourceStatEnum getResourceStatEnum(int state) {

		for (ResourceStatEnum resourceStatEnum : values()) {
			if (resourceStatEnum.state == state) {
				return resourceStatEnum;
			}
		}

		return null;

	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

}
