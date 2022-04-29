package com.dataeval.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dataeval.model.entity.FlowPage;
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PageRepositoryTest {
	
//	@Autowired
//	private PageRepository pageRepository;
//
//	@Test
//	void testFindAllByStatusOrderBySequenceAsc() {
//		fail("Not yet implemented");
//	}
//
////	@Test
//	void testLookupPages() {
//		
//		List<FlowPage> pages = pageRepository.lookupPages();
//		System.out.print("Size ::" + pages.size());
//		assertNotNull(pages);
//	}

}
