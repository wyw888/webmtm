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
		 logger.info("通过@PathVariable获得数据  " + userId);
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("message","通过@PathVariable获得数据  " + userId);
		 mv.setViewName("test");
		 return mv;
	 }
	
	// http://localhost:8080/DataBindingTest/requestHeaderTest
	@RequestMapping(value="/requestHeaderTest")
	 public ModelAndView requestHeaderTest(
			 @RequestHeader("User-Agent") String userAgent,  
		     @RequestHeader(value="Accept") String[] accepts) {
		 logger.info("通过@requestHeaderTest获得数据 " + userAgent);
		 for(String accept : accepts){
			 logger.info(accept);
		 }
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("message","通过@requestHeaderTest获得数据 " + userAgent);
		 mv.setViewName("test");
		 return mv;
	 }
	
	// 娴嬭瘯@CookieValue娉ㄨВ
	@RequestMapping(value="/cookieValueTest")
	 public ModelAndView cookieValueTest(
			 @CookieValue(value="JSESSIONID", defaultValue="") String sessionId) {
		 logger.info("通过@requestHeaderTest获得数据  " + sessionId);
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("message","通过@requestHeaderTest获得数据  " + sessionId);
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
		// 查看查询到的user对象信息
		System.out.println(user.getId() + " " + user.getUsername());
		// 查看user对象关联的订单信息
		List<Order> orders = user.getOrders();
		for(Order order : orders){
			System.out.println(order);
		}
		session.commit();
		session.close();
        model.addAttribute("orders", orders);
        return "showperson";
    }
    
	// 测试一对多，查询班级User（一）的时候级联查询订单Order（多）  
	public void testSelectUserById(SqlSession session){
		// 获得UserMapper接口的代理对象
		UserMapper um = session.getMapper(UserMapper.class);
		// 调用selectUserById方法
		User user = um.selectUserById(1);
		// 查看查询到的user对象信息
		System.out.println(user.getId() + " " + user.getUsername());
		// 查看user对象关联的订单信息
		List<Order> orders = user.getOrders();
		for(Order order : orders){
			System.out.println(order);
		}
	}
	
    
	
	// 测试多对多，查询订单Order（多）的时候级联查询订单的商品Article（多）  
	public void testSelectOrderById(SqlSession session){
		// 获得OrderMapper接口的代理对象
		OrderMapper om = session.getMapper(OrderMapper.class);
		// 调用selectOrderById方法
		Order order = om.selectOrderById(2);
		// 查看查询到的order对象信息
		System.out.println(order.getId() + " " + order.getCode() + " " + order.getTotal());
		// 查看order对象关联的用户信息
		User user = order.getUser();
		System.out.println(user);
		// 查看order对象关联的商品信息
		List<Article> articles = order.getArticles();
		for(Article article : articles){
			System.out.println(article);
		}
		
	}


	
}
