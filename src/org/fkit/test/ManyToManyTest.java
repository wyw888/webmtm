package org.fkit.test;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fkit.domain.Article;
import org.fkit.domain.Order;
import org.fkit.domain.User;
import org.fkit.mapper.ArticleMapper;
import org.fkit.mapper.OrderMapper;
import org.fkit.mapper.UserMapper;


public class ManyToManyTest {

	public static void main(String[] args) throws Exception {
		// ��ȡmybatis-config.xml�ļ�
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		// ��ʼ��mybatis������SqlSessionFactory���ʵ��
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		// ����Sessionʵ��
		SqlSession session = sqlSessionFactory.openSession();
		
		ManyToManyTest t = new ManyToManyTest();
		
		// �����û�id��ѯ�û�������һ�Զ�
//		t.testSelectUserById(session);
		// ���ݶ���id��ѯ���������Զ�Զ�
	
		
		t.testSelectUserById(session);
		t.testSelectOrderById(session);
		t.testSelectArticleById(session);
		
		// �ύ����
		session.commit();
		// �ر�Session
		session.close();
	}
	
	// ����һ�Զ࣬��ѯ�༶User��һ����ʱ������ѯ����Order���ࣩ  
	public void testSelectUserById(SqlSession session){
		// ���UserMapper�ӿڵĴ������
		UserMapper um = session.getMapper(UserMapper.class);
		// ����selectUserById����
		User user = um.selectUserById(1);
		// �鿴��ѯ����user������Ϣ
		System.out.println(user.getId() + " " + user.getUsername());
		// �鿴user��������Ķ�����Ϣ
		List<Order> orders = user.getOrders();
		for(Order order : orders){
			System.out.println(order);
		}
	}
	
	// ���Զ�Զ࣬��ѯ����Order���ࣩ��ʱ������ѯ��������ƷArticle���ࣩ  
	public void testSelectOrderById(SqlSession session){
		// ���OrderMapper�ӿڵĴ������
		OrderMapper om = session.getMapper(OrderMapper.class);
		// ����selectOrderById����
		Order order = om.selectOrderById(2);
		// �鿴��ѯ����order������Ϣ
		System.out.println(order.getId() + " " + order.getCode() + " " + order.getTotal());
		// �鿴order����������û���Ϣ
		User user = order.getUser();
		System.out.println(user);
		// �鿴order�����������Ʒ��Ϣ
		List<Article> articles = order.getArticles();
		for(Article article : articles){
			System.out.println(article);
		}
	}
	
	 
	public void testSelectArticleById(SqlSession session){
		// ���ArticleMapper�ӿڵĴ������
		ArticleMapper om = session.getMapper(ArticleMapper.class);
		// ����selectArticleByOrderId����
		List<Article> articles=om.selectArticleByOrderId(1);
		// �鿴��ѯ����Article������Ϣ
		for(Article article : articles){
			System.out.println(article);
		}

	}

}
