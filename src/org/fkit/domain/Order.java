package org.fkit.domain;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

	private Integer id;  // ����id������
	private String code;  // �������
	private Double total; // �����ܽ��
	
	// �������û��Ƕ��һ�Ĺ�ϵ����һ������ֻ����һ���û�
	private User user;
	
	// ��������Ʒ�Ƕ�Զ�Ĺ�ϵ����һ���������԰���������Ʒ
	private List<Article> articles;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String code, Double total) {
		super();
		this.code = code;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", code=" + code + ", total=" + total + "]";
	}
	
	
}
