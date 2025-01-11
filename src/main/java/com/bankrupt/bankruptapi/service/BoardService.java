package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private final CategoryRelationService categoryRelationService;

    @Transactional
    public void saveBoardList(ArrayList<Board> boardList) {
        boardList.forEach(board -> {
            boardRepository.save(board);
            categoryRelationService.saveAllCategoryRelationByBoard(board);
        });
    }

    public List<Long> findAllBoardIdList() {
        return boardRepository.findAllBoardIds();
    }

    public List<Board> findAllBoardList(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = getPageRequest(page, size, sort, direction);
        return boardRepository.findAll(pageable).getContent();
    }

    public List<Board> findAllBoardByCategoryId(Long categoryId, Integer page, Integer size, String sort, String direction) {
        Pageable pageable = getPageRequest(page, size, sort, direction);
        return boardRepository.findAllBoardByCategoryId(categoryId, pageable);
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
