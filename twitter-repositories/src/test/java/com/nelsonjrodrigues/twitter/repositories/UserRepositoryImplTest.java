package com.nelsonjrodrigues.twitter.repositories;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.nelsonjrodrigues.twitter.data.model.User;

public class UserRepositoryImplTest extends AbstractRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void testAddUser() {
		User user = new User();

		user.setUsername("test");

		repository.save(user);

		int rowsInTable = countRowsInTable("Users");

		Assert.assertEquals(1, rowsInTable);

		User retrievedUser = simpleJdbcTemplate.queryForObject("select * from Users where id = ?", new BeanPropertyRowMapper<>(User.class),
				user.getId());

		Assert.assertEquals(user, retrievedUser);

	}

	@Test
	public void testRetrieveUser() {
		String id = UUID.randomUUID().toString();
		String username = "test";

		simpleJdbcTemplate.update("insert into Users values (?, ?)", id, username);

		User retrievedUser = repository.load(id);

		Assert.assertNotNull(retrievedUser);
		Assert.assertEquals(id, retrievedUser.getId());
		Assert.assertEquals(username, retrievedUser.getUsername());

	}

}
