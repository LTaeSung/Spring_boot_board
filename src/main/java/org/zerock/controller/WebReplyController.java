package org.zerock.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.WebBoard;
import org.zerock.domain.WebReply;
import org.zerock.persistence.WebReplyRepository;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;

@RestController
@Log
@RequestMapping("/replies/")
@Tag(name = "댓글API")
public class WebReplyController {

	@Autowired
	private WebReplyRepository replyRepo;
	
	@Transactional
	@PostMapping("/{bno}") // /replies/값
	public ResponseEntity<List<WebReply>> addReply(@PathVariable("bno") Long bno, @RequestBody WebReply reply) {
		log.info("bno:"+bno);
		log.info("reply:"+reply);
		WebBoard board = new WebBoard();
		board.setBno(bno);
		reply.setBoard(board);
		replyRepo.save(reply);
		return new ResponseEntity<>(getListByBoard(board),HttpStatus.CREATED);
	}
	
	private List<WebReply> getListByBoard(WebBoard board) throws RuntimeException {
		log.info("getListByBoard..."+board);
		return replyRepo.getRepliesOfBoard(board);
	}

	@Parameters({
			@Parameter(description = "게시글 pk", name = "bno"),
			@Parameter(description = "댓글 pk", name = "rno")
	})
	@Transactional
	@DeleteMapping("/{bno}/{rno}") // /replies/값
	public ResponseEntity<List<WebReply>> remove(
			@PathVariable("bno") Long bno, 
			@PathVariable("rno") Long rno) {
		replyRepo.deleteById(rno);
		
		WebBoard board = new WebBoard();
		board.setBno(bno);
		return new ResponseEntity<>(getListByBoard(board),HttpStatus.OK);
	}
	@Transactional
	@PutMapping("/{bno}") // /replies/값
	public ResponseEntity<List<WebReply>> modify(
			@PathVariable("bno") Long bno, 
			@RequestBody WebReply reply) {
		replyRepo.findById(reply.getRno()).ifPresent(origin->{
			origin.setReplyText(reply.getReplyText());
			replyRepo.save(origin);
		});
		
		WebBoard board = new WebBoard();
		board.setBno(bno);
		return new ResponseEntity<>(getListByBoard(board),HttpStatus.OK);
	}
	
	@Operation(description = "해당게시글의 댓글 목록")
	@GetMapping("/{bno}") // /replies/값
	public ResponseEntity<List<WebReply>> getReplies(
			@Parameter(description = "게시글의 pk") @PathVariable("bno") Long bno) {
		
		WebBoard board = new WebBoard();
		board.setBno(bno);
		return new ResponseEntity<>(getListByBoard(board),HttpStatus.OK);
	}
	
}
