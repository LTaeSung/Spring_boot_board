package org.zerock;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.zerock.domain.WebBoard;
import org.zerock.persistence.WebBoardRespository;

import lombok.extern.java.Log;

@SpringBootTest
@Log 
@Commit
class WebBoardRepositoryTests {
	@Autowired
	WebBoardRespository repo;
	
	@Test
	void insertBoardDummies() {
		IntStream.range(0,300).forEach(i->{
			WebBoard board = new WebBoard();
			board.setTitle("Sample Board Title "+i);
			board.setContent("Content Sample ..."+i+" of Board ");
			board.setWriter("user0"+i%10);
			repo.save(board);
		});
	}
	
	@Test
	void testList1() {
		Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "bno");
		Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), pageable);
		
		log.info("PAGE: "+result.getPageable());
		log.info("---------------------");
		result.getContent().forEach(board->log.info(""+board));
	}
	@Test
	void testList2() {
		Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "bno");
		Page<WebBoard> result = repo.findAll(repo.makePredicate("t", "10"), pageable);
		
		log.info("PAGE: "+result.getPageable());
		log.info("---------------------");
		result.getContent().forEach(board->log.info(""+board));
	}



}
