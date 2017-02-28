package com.yangcb.seckill.test.myenum;

public enum Car {
	BMW(2,"宝马");
	private int num;
	private String color;
	
	Car(int num,String color){
		this.num=num;
		this.color=color;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static Car getNum(int num){
		
		for(Car car:values()){
			if(car.num==num){
				return car;
				
			}
		}
		
		return null;
	}
	
	
	
	
	
}
