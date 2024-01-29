package org.zerock;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.persistence.*;
import java.util.Arrays;

@WebAppConfiguration
@SpringBootTest
@Log 
@Commit
class CustomRepositoryTests {
	@Autowired
	CustomCrudRepository repo;

	@Test
	public void test1() {
		Pageable pageable = PageRequest.of(0, 10, Direction.DESC, "bno");

		String type ="t";
		String keyword = "10";

		Page<Object[]> result = repo.getCustomPage(type, keyword, pageable);

		log.info(""+result);
		log.info("Total page :"+ result.getTotalPages());
		log.info("Total size :"+ result.getTotalElements());

		result.getContent().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}

	@Test
	public void testWriter(){
		Pageable pageable = PageRequest.of(0, 10, Direction.DESC, "bno");

		String type = "w";
		String keword = "user09";

		Page<Object[]> result = repo.getCustomPage(type, keword, pageable);

		log.info(""+result);
		log.info("total page: "+result.getTotalPages());
		log.info("total size: "+result.getTotalElements());

		result.getContent().forEach(arr ->{
			log.info(Arrays.toString(arr));
		});
	}
}
