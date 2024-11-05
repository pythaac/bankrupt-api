package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.repository.BoardRepository;
import com.bankrupt.bankruptapi.repository.CategoryRelationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRelationRepository categoryRelationRepository;

    private final CategoryRelationService categoryRelationService;

    @Transactional
    public List<Long> getAllBoardIdList() {
        return boardRepository.getBoardIds();
    }

    @Transactional
    public void saveBoards(ArrayList<Board> boards) {
        boards.forEach(board -> {
            boardRepository.save(board);
            categoryRelationService.saveCategoryRelationsByBoard(board);
        });
    }

    @Transactional
    public void deleteBoardsByIdList(List<Long> boardIdList) {
        boardRepository.deleteAllById(boardIdList);
        categoryRelationRepository.deleteAllByBoardIdIn(boardIdList);
    }
}
