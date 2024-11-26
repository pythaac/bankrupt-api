package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.service.BoardService;
import com.bankrupt.bankruptapi.service.ScourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController(value = "/v1/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public List<Board> getAllBoard() {
        return boardService.findAllBoardList();
    }

    @GetMapping(value = "/{categoryId}")
    public List<Board> getBoardById(@PathVariable Long categoryId) {
        return boardService.findAllBoardByCategoryId(categoryId);
    }
}
