package org.fkit.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fkit.domain.Article;
import org.fkit.domain.Order;
import org.fkit.domain.User;
import org.fkit.mapper.OrderMapper;
import org.fkit.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManyToManyController {

	private static final Log logger = LogFactory
            .getLog(ManyToManyController.class);

	// http://localhost:8080/DataBindingTest/pathVariableTest/{userId}
	@RequestMapping(value="/pathVariableTest/{userId}")
	 public ModelAndView pathVariableTest(
			 @PathVariable Integer userId) {
		 logger.info("ͨ��@PathVariable�������  " + userId);
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("message","ͨ��@PathVariable�������  " + userId);
		 mv.setViewName("test");
		 return mv;
	 }
	
	// http://localhost:8080/DataBindingTest/requestHeaderTest
	@RequestMapping(value="/requestHeaderTest")
	 public ModelAndView requestHeaderTest(
			 @RequestHeader("User-Agent") String userAgent,  
		     @RequestHeader(value="Accept") String[] accepts) {
		 logger.info("ͨ��@requestHeaderTest������� " + userAgent);
		 for(String accept : accepts){
			 logger.info(accept);
		 }
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("message","ͨ��@requestHeaderTest������� " + userAgent);
		 mv.setViewName("test");
		 return mv;
	 }
	
	// 测试@CookieValue注解
	@RequestMapping(value="/cookieValueTest")
	 public ModelAndView cookieValueTest(
			 @CookieValue(value="JSESSIONID", defaultValue="") String sessionId) {
		 logger.info("ͨ��@requestHeaderTest�������  " + sessionId);
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("message","ͨ��@requestHeaderTest�������  " + sessionId);
		 mv.setViewName("test");
		 return mv;
	 }
	
	
    @RequestMapping("/showPerson")
    public String showPersons(Model model) throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("/mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		
		UserMapper um = session.getMapper(UserMapper.class);
		User user = um.selectUserById(1);
		// �鿴��ѯ����user������Ϣ
		System.out.println(user.getId() + " " + user.getUsername());
		// �鿴user��������Ķ�����Ϣ
		List<Order> orders = user.getOrders();
		for(Order order : orders){
			System.out.println(order);
		}
		session.commit();
		session.close();
        model.addAttribute("orders", orders);
        return "showperson";
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


	
}
