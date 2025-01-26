package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.dao.CategoryResource;
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
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private final CategoryService categoryService;
    private final CategoryResourceService categoryResourceService;
    private final CategoryRelationService categoryRelationService;

    public Long getTotalBoardCount(Long categoryId) {
        if (Objects.isNull(categoryId)) {
            return boardRepository.count();
        }
        return boardRepository.countByCategoryId(categoryId);
    }

    @Transactional
    public void saveBoard(Board board) {
        boardRepository.save(board);
        saveAllCategoryRelationByBoard(board);
    }

    @Transactional
    public void saveBoardList(List<Board> boardList) {
        boardList.forEach(this::saveBoard);
    }

    public List<Long> findAllBoardIdList() {
        return boardRepository.findAllBoardIds();
    }

    public List<Board> findAll() { return boardRepository.findAll(); }

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
            return PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, sort));
        } else {
            return PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC, sort));
        }
    }

    private void saveAllCategoryRelationByBoard(Board board) {
        List<CategoryResource> allCategoryResources = categoryResourceService.findAllCategoryResources();

        Map<Long, List<CategoryResource>> categoryResourceMapByCategoryId = allCategoryResources.stream()
                .collect(Collectors.groupingBy(CategoryResource::getCategoryId));

        categoryResourceMapByCategoryId.entrySet().stream().parallel()
                .forEach(entry -> categoryRelationService.saveCategoryRelation(entry, board));
    }
}
