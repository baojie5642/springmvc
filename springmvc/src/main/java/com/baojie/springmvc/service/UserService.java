package com.baojie.springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import javax.annotation.Resource;

import javax.persistence.criteria.Path;



import org.springframework.data.domain.PageRequest;


import org.springframework.data.domain.Sort.Direction;


import com.baojie.springmvc.dao.UserDao;
import com.baojie.springmvc.model.User;


/*
 * springdata jpa总结的查询方法有如下：

findOneByName按用户名查询User对象
findAllByLargeThanId查询id号大于指定参数值的User对象列表，分页Page返回
findAllByIdAndName查询id和用户名等于指定参数值的User对象，可以通过多个Predicate来查询
findAllByLikeName模糊分页查询用户名，like查询
findAllByInIds查询指定的id集合用户对象列表
findAllByIds通过原生sql查询指定的用户id对象列表
findAllByName通过JPA query查询用户名等于指定值的用户对象
多个排序语法: Sort sort = new Sort(Direction.DESC, "sort").and(new Sort(Direction.DESC, "id"));

作者：javaniu
链接：https://www.zhihu.com/question/37873032/answer/73991650
来源：知乎
著作权归作者所有，转载请联系作者获得授权。
 */

@Service
public class UserService {
	private final ThreadLocalRandom random = ThreadLocalRandom.current();

	@Autowired
	private UserDao userDao;
	
	//@Autowired
	//private PageQueryDao pageQueryDao;


	public String add(User user) {
		userDao.save(user);
		return "添加成功！";
	}

	public String addUser(String account) {
		final int level = random.nextInt(5);
		User user = new User();
		user.setAccount(account);
		user.setLevel(level);
		userDao.save(user);
		return "添加成功！";
	}

	public User getOneUser(Long id) {
		return userDao.findOne(id);
	}


//***********************************************************************************************************
	

	
		public User findOneByName(final String name) {
			Specification<User> specification = new Specification<User>() {
				@Override
				public Predicate toPredicate(Root<User> root,
						CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
					Predicate _name = criteriaBuilder.equal(root.get("account"), name);
					return criteriaBuilder.and(_name);
				}
			};
			return userDao.findOne(specification);
		}

		
		public Page<User> findAllByEqualsLevel(final Integer id, int page, int count) {//,Sort sort
			Specification<User> specification = new Specification<User>() {
				@Override
				public Predicate toPredicate(Root<User> root,
						CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
					Path<Long> $id = root.get("level");
					Predicate _id = criteriaBuilder.equal($id, id);
					return criteriaBuilder.and(_id);
				}
			};
			PageRequest pageRequest = new PageRequest(page - 1, count );//,sort
			return userDao.findAll(specification, pageRequest);
		}

		
//		public User findAllByIdAndName(final Long id, final String name) {
//			Specification<User> specification = new Specification<User>() {
//				@Override
//				public Predicate toPredicate(Root<User> root,
//						CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//					List<Predicate> predicates = new ArrayList<Predicate>();
//					Path<Long> $id = root.get("id");
//					Predicate _id = criteriaBuilder.equal($id, id);
//					predicates.add(_id);
//					Path<Long> $name = root.get("name");
//					Predicate _name = criteriaBuilder.equal($name, name);
//					predicates.add(_name);
//					return criteriaBuilder.and(predicates
//							.toArray(new Predicate[] {}));
//				}
//			};
//			return pageQueryDao.findOne(specification);
//		}

		
//		public Page<User> findAllByLikeName(final String name, int page, int count) {
//			Specification<User> specification = new Specification<User>() {
//				@Override
//				public Predicate toPredicate(Root<User> root,
//						CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//					Path<String> _name = root.get("name");
//					Predicate _key = criteriaBuilder.like(_name, "%" + name + "%");
//					return criteriaBuilder.and(_key);
//				}
//			};
//			Sort sort = new Sort(Direction.DESC, "id");
//			Pageable pageable = new PageRequest(page - 1, count, sort);
//			return pageQueryDao.findAll(specification, pageable);
//		}

		
//		public Page<User> findAllByInIds(final List<Long> ids) {
//			Specification<User> specification = new Specification<User>() {
//				@Override
//				public Predicate toPredicate(Root<User> root,
//						CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//					return criteriaBuilder.in(root.get("id")).value(ids);
//				}
//			};
//			int page = 1;
//			int count = ids.size();
//			Sort sort = new Sort(Direction.DESC, "id");
//			Pageable pageable = new PageRequest(page - 1, count, sort);
//			return pageQueryDao.findAll(specification, pageable);
//		}

		
//		public List<User> findAllByIds(List<Long> ids) {
//			return pageQueryDao.findAllByUserId(ids);
//		}

		
//		public User findAllByName(String name) {
//			return pageQueryDao.findAllByName(name);
//		}

}