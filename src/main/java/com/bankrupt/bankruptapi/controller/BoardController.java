package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@RequiredArgsConstructor
@RestController(value = "/v1/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public List<Board> getAllBoard(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "uploaded")
                @Pattern(regexp = "uploaded|index|seller|title|due|category") String sort,
            @RequestParam(required = false, defaultValue = "desc")
                @Pattern(regexp = "asc|desc") String direction
        ) {
        return boardService.findAllBoardList(page, size, sort, direction);
    }

    @GetMapping(value = "/{categoryId}")
    public List<Board> getBoardById(@PathVariable Long categoryId) {
        return boardService.findAllBoardByCategoryId(categoryId);
    }
}
