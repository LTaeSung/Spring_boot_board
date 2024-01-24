package org.zerock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import lombok.extern.java.Log;

@SpringBootTest
@Log 
@Commit
class MyBatisTest {
	@Autowired
	WebBoardMapper mapper;
	
	@Test
	void mybatis() {
		mapper.all().forEach(m->{
			System.out.println(m);
		});
	}
	

}
