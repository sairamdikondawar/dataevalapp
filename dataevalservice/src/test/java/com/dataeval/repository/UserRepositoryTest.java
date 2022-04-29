package com.dataeval.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dataeval.model.entity.User;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

//	@Autowired
//	private UserRepository userRepository;
//
////	@Test
//	void testFindByUserName() {
//
//		Optional<User> user = userRepository.findByUserName("admin");
//
//		assertTrue(user.isPresent());
//
//		assertEquals(user.get().getUserName(), "admin");
//	}
//
//	@Test
//	void testFindAllPatinetsStringPageable() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllPatinetsInteger() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllUsers() {
//		fail("Not yet implemented");
//	}

}
