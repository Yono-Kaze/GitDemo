package com.springboot.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Employee {
    private Integer empid;
    
    @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})"
    		,message="用户名必须是2-5位的中文或者6-11的英文和数字的组合")
    private String empname;

    //性别0为男 1为女
    private Integer sex;
    
    
	@DateTimeFormat(pattern = "yyyy-MM-dd" )//处理前台传递给后台时候的问题
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//处理date数据在后台传给前台时候转化为json数据时候显示不正确的问题
    private Date date;
	
    @Pattern(regexp = "(^[0-9_-]{3,11}$)"
    		,message="后端校验：电话只能由纯数字的3-11数字组成")
    private String phone;

    private Integer did;
    
    private  Department department;

    
    
 


	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", empname=" + empname + ", sex=" + sex + ", date=" + date + ", phone="
				+ phone + ", did=" + did + ", department=" + department + "]";
	}



	public Employee(Integer empid,
			@Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[⺀-鿿]{2,5})", message = "用户名必须是2-5位的中文或者6-11的英文和数字的组合") String empname,
			Integer sex, Date date, String phone, Integer did, Department department) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.sex = sex;
		this.date = date;
		this.phone = phone;
		this.did = did;
		this.department = department;
	}



	public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname == null ? null : empname.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }



	public Department getDepartment() {
		return department;
	}



	public void setDepartment(Department department) {
		this.department = department;
	}
}