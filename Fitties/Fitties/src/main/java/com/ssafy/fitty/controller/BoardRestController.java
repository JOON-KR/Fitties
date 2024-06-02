package com.ssafy.fitty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitty.model.dto.board.BoardFree;
import com.ssafy.fitty.model.dto.board.LikeCon;
import com.ssafy.fitty.model.dto.util.SearchCondition;
import com.ssafy.fitty.model.service.board.BoardFreeService;
import com.ssafy.fitty.model.service.board.LikeConService;
import com.ssafy.fitty.security.JwtTokenProvider;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
//검증완료
@RestController
@RequestMapping("/boardFree")
@CrossOrigin(value="*")
@Tag(name="BoardRestController", description = "게시판 CRUD")
public class BoardRestController {
	private final BoardFreeService boardFreeService;
	private final JwtTokenProvider jwtTokenProvider;
	private final LikeConService likeConService;
	
	@Autowired
	public BoardRestController(BoardFreeService boardFreeService, 
			JwtTokenProvider jwtTokenProvider,
			LikeConService likeConService) {
		this.boardFreeService = boardFreeService;
		this.jwtTokenProvider = jwtTokenProvider;
		this.likeConService = likeConService;
	}
	
	@GetMapping
	@Operation(summary = "전체 조회", description = "게시글 전체 조회")
	public ResponseEntity<?> list(@Parameter(description = "검색 조건", required = false) SearchCondition condition){
	    List<BoardFree> list;
	    if(condition != null) {
	        list = boardFreeService.search(condition); //검색 조회
	    } else {
	        list = boardFreeService.getBoardList(); //전체 조회
	    }
	    if(list == null || list.size() == 0) {
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<List<BoardFree>>(list, HttpStatus.OK);
	}
	
	//게시글 상세 보기
	@GetMapping("/{num}")
	public ResponseEntity<BoardFree> detail(@PathVariable("num") int num){
		BoardFree boardFree = boardFreeService.readBoard(num); //조회수도 하나 증가 하더라!
		//가져왔는데 board 가 null이면 예외처리를 해줘라! 404 처리! (직접 해볼것 ㅎ)
		return new ResponseEntity<BoardFree>(boardFree, HttpStatus.OK);		
	}
	
	//게시글 등록 (Form 형식으로 넘어왔을 때)
	@PostMapping
	public ResponseEntity<?> write(@ModelAttribute BoardFree boardFree){
		//등록한 게시글을 보냈는데
		boardFreeService.writeBoard(boardFree);
		//등록이 되어있는지 눈으로 Talend API 보려고 이렇게 보낸거지
		//실제로 프론트에게 보낼때는 크게 의미는 없다! ID만 보내서 디테일 쏘던지 바로 목록으로가면 필요없다!
		//insert, update, delete -> 반환값이 int 형의 값이 넘어온다. (바뀐 행의 개수가 반환됨)
		return new ResponseEntity<BoardFree>(boardFree, HttpStatus.CREATED);
	}	
	
	//게시글 수정 (JSON형태로 보낸다)
//	@PutMapping("/board")
	@PutMapping("/{num}")
	public ResponseEntity<Void> update(@PathVariable("num") int num, @RequestBody BoardFree boardFree){
		boardFree.setNum(num);
		boardFreeService.modifyBoard(boardFree); //id를 따로 보내왔다면 바구니(DTO)에 넣어놓고 보내자
		return new ResponseEntity<Void>(HttpStatus.OK); //조금 더 세밀하게 컨트롤 할 수도 있다.
	}
	
	//게시글 삭제 
	@Hidden
	@DeleteMapping("/{num}")
	public ResponseEntity<Void> delete(@PathVariable("num") int num){
		//반환값에 따라서 실제로 지워졌는지 or 내가 없는 글을 지우려고 하지는 않는지... 등의 상황에따라
		//응답코드가 바뀌면 프론트에서 처리하기가 더욱 수월해 지겠다.!
		boardFreeService.removeBoard(num);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/press")
	public ResponseEntity<String> pressHeart(@RequestParam("token")String token, 
			@RequestParam("board_num") int board_num) {
		String user_id = jwtTokenProvider.getUserId(token);
		LikeCon likeCon = new LikeCon(board_num, user_id);
		likeConService.pressHeart(likeCon);
		return new ResponseEntity<>("Liked", HttpStatus.OK);
	}
	
	@DeleteMapping("/cancel")
	public ResponseEntity<String> cancelHeart(@RequestParam("token")String token, 
			@RequestParam("board_num") int board_num) {
		String user_id = jwtTokenProvider.getUserId(token);
		LikeCon likeCon = new LikeCon(board_num, user_id);
        likeConService.cancelHeart(likeCon);
        return new ResponseEntity<>("Cancelled", HttpStatus.OK);
    }
}
