package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baojie.springmvc.dao.UserDao;
import com.baojie.springmvc.service.UserService;

public class Test {

	public Test() {

	}

	public static void main(String args[]) {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = applicationContext.getBean(UserService.class);
		UserDao userDao=applicationContext.getBean(UserDao.class);
		System.out.println(userDao.count());
		
		//userService.addUser("liuxin_"+1);
		//for(int i=1;i<=10;i++){
		//	userService.addUser("liuxin_"+i);
		//}
		
	}
	
}
