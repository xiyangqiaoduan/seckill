package com.yangcb.seckill.demo;
@YcbTable("tb_student")
public class YcbStudent {
	@YcbField(cloumnName="id",type="int",length=10)
	private int id;
	@YcbField(cloumnName="sname",type="varchar",length=10)
	private String studentName;
	@YcbField(cloumnName="age",type="int",length=10)
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
