package com.ssafy.fitty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitty.model.dto.fitty.BoardFitty;
import com.ssafy.fitty.model.dto.util.SearchCondition;
import com.ssafy.fitty.model.service.fitty.BoardFittyService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

//검증완료
@RestController
@RequestMapping("/boardFitty")
@CrossOrigin(value="*")
@Tag(name="BoardFittyRestController", description = "파티 게시판 CRUD")
public class BoardFittyRestController {
    private final BoardFittyService boardFittyService;

    @Autowired
    public BoardFittyRestController(BoardFittyService boardFittyService) {
        this.boardFittyService = boardFittyService;
    }

    @GetMapping
    public ResponseEntity<List<BoardFitty>> getBoardFittyList() {
        List<BoardFitty> boardFittyList = boardFittyService.getBoardFittyList();
        return new ResponseEntity<>(boardFittyList, HttpStatus.OK);
    }

    @GetMapping("/{num}")
    public ResponseEntity<BoardFitty> readBoardFitty(@PathVariable int num) {
        BoardFitty boardFitty = boardFittyService.readBoardFitty(num);
        return new ResponseEntity<>(boardFitty, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> writeBoardFitty(@RequestBody BoardFitty boardFitty) {
        boardFittyService.writeBoardFitty(boardFitty);
        return new ResponseEntity<>("BoardFitty created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<String> removeBoardFitty(@PathVariable int num) {
        boardFittyService.removeBoardFitty(num);
        return new ResponseEntity<>("BoardFitty deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{num}")
    public ResponseEntity<String> modifyBoardFitty(@PathVariable int num, @RequestBody BoardFitty boardFitty) {
        boardFitty.setNum(num);
        boardFittyService.modifyBoardFitty(boardFitty);
        return new ResponseEntity<>("BoardFitty updated successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BoardFitty>> searchFitty(@RequestBody SearchCondition searchCondition) {
        List<BoardFitty> boardFittyList = boardFittyService.searchFitty(searchCondition);
        return new ResponseEntity<>(boardFittyList, HttpStatus.OK);
    }
}
