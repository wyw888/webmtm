package org.fkit.domain;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
	
	private Integer id;  // �û�id������
	private String username;  // �û���
	private String loginname; // ��¼��
	private String password;  // ����
	private String phone;    // ��ϵ�绰
	private String address;  // �ջ���ַ
	
	// �û��Ͷ�����һ�Զ�Ĺ�ϵ����һ���û������ж������
	private List<Order> orders;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String loginname, String password,
			String phone, String address) {
		super();
		this.username = username;
		this.loginname = loginname;
		this.password = password;
		this.phone = phone;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", loginname="
				+ loginname + ", password=" + password + ", phone=" + phone
				+ ", address=" + address + "]";
	}


}
