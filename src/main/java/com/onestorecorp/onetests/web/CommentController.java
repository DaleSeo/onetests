package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.common.SecurityUtils;
import com.onestorecorp.onetests.domain.Comment;
import com.onestorecorp.onetests.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@RestController
@Slf4j
public class CommentController {

	private final CommentRepository commentRepo;

	public CommentController(CommentRepository commentRepo) {
		this.commentRepo = commentRepo;
	}

	@GetMapping("/api/comments")
	public @ResponseBody List<Comment> get(@RequestParam String caseId) {
		log.debug("get");
		Comment comment = new Comment();
		if (caseId != null) {
			comment.setCaseId(caseId);
		}
		List<Comment> comments = commentRepo.findAll(Example.of(comment));
		comments.stream()
				.forEach(cmt -> cmt.setAuthor(cmt.getCreatedBy().getName()));
		return comments;
	}

	@PostMapping("/api/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@RequestBody Comment comment) {
		log.debug("post: {}", comment);
		commentRepo.save(comment);
	}

	@DeleteMapping("/api/comments/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		log.debug("delete: {}", id);
		Comment comment = commentRepo.findOne(id);
		if (SecurityUtils.isCurrentUser(comment.getCreatedBy())) {
			commentRepo.delete(id);
		} else {
			throw new RuntimeException(HttpStatus.FORBIDDEN.toString());
		}
	}

	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception exception) {
		HttpStatus httpStatus = HttpStatus.valueOf(exception.getMessage());
		return new ResponseEntity<>(httpStatus);
	}

}
