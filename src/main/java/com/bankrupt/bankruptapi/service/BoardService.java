package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.dto.BoardDto;
import com.bankrupt.bankruptapi.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private final CategoryRelationService categoryRelationService;
    private final CategoryService categoryService;

    public Long getTotalBoardCount(Long categoryId) {
        if (Objects.isNull(categoryId)) {
            return boardRepository.count();
        }
        return boardRepository.countByCategoryId(categoryId);
    }

    @Transactional
    public void saveBoard(Board board) {
        boardRepository.save(board);
        categoryRelationService.saveAllCategoryRelationByBoard(board);
    }

    @Transactional
    public void saveBoardList(List<Board> boardList) {
        boardList.forEach(this::saveBoard);
    }

    public List<Long> findAllBoardIdList() {
        return boardRepository.findAllBoardIds();
    }

    public List<BoardDto> findAllBoardList(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = getPageRequest(page, size, sort, direction);
        List<Board> boards = boardRepository.findAll(pageable).getContent();

        return boards.stream()
                .map(board -> BoardDto.of(board, categoryService.findAllCategoryByBoardId(board.getId())))
                .toList();
    }

    public List<BoardDto> findAllBoardByCategoryId(Long categoryId, Integer page, Integer size, String sort, String direction) {
        Pageable pageable = getPageRequest(page, size, sort, direction);
        List<Board> boards = boardRepository.findAllBoardByCategoryId(categoryId, pageable);

        return boards.stream()
                .map(board -> BoardDto.of(board, categoryService.findAllCategoryByBoardId(board.getId())))
                .toList();
    }

    @Transactional
    public void deleteByBoardIdList(List<Long> boardIdList) {
        boardRepository.deleteAllById(boardIdList);
        categoryRelationService.deleteAllCategoryRelationByBoardIdList(boardIdList);
    }

    private PageRequest getPageRequest(Integer page, Integer size, String sort, String direction) {
        if ("asc".equals(direction)) {
            return PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort));
        } else {
            return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sort));
        }
    }
}
