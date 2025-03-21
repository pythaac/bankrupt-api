package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.dto.BoardDto;
import com.bankrupt.bankruptapi.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping(value = "/count")
    public Long getTotalBoardCount(@RequestParam(required = false) Long categoryId) {
        return boardService.getTotalBoardCount(categoryId);
    }

    @GetMapping
    public List<BoardDto> getAllBoard(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id")
                @Pattern(regexp = "id|uploaded|court|seller|title|due") String sort,
            @RequestParam(defaultValue = "desc")
                @Pattern(regexp = "asc|desc") String direction
    ) {
        return boardService.findAllBoardList(page, size, sort, direction);
    }

    @GetMapping(value = "/{categoryId}")
    public List<BoardDto> getBoardById(
            @PathVariable Long categoryId,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "id")
                @Pattern(regexp = "id|uploaded|court|seller|title|due") String sort,
            @RequestParam(required = false, defaultValue = "desc")
                @Pattern(regexp = "asc|desc") String direction
    ) {
        return boardService.findAllBoardByCategoryId(categoryId, page, size, sort, direction);
    }
}
