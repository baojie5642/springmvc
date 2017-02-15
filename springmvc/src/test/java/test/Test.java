package test;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.baojie.springmvc.dao.UserDao;
import com.baojie.springmvc.model.User;
import com.baojie.springmvc.service.UserService;

public class Test {

	public Test() {

	}

	public static void main(String args[]) {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = applicationContext.getBean(UserService.class);
		Page<User> page = userService.findAllByEqualsLevel(3, 3, 100);// ,new
																		// Sort(Direction.DESC)
		System.out.println("ddk");
		List<User> list = null;
		User user = null;
		Iterator<User> uIterable = null;
		uIterable = page.iterator();
		if (null != page) {
			while (uIterable.hasNext()) {
				user = uIterable.next();
				// uIterable.
				System.out.println(user.getAccount());
			}
		} else {
			System.out.println("page null.");
		}

	}

}
