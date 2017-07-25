package org.fkit.domain;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {
	
	private Integer id;		// ��Ʒid������
	private String name;	// ��Ʒ����
	private Double price;	// ��Ʒ�۸�
	private String remark;	// ��Ʒ����
	
	// ��Ʒ�Ͷ����Ƕ�Զ�Ĺ�ϵ����һ����Ʒ���԰����ڶ��������
	private List<Order> orders;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(String name, Double price, String remark) {
		super();
		this.name = name;
		this.price = price;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", price=" + price
				+ ", remark=" + remark + "]";
	}

}
