package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.CategoryRow;
import com.bankrupt.bankruptapi.repository.CategoryRowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryRowService {
    private final CategoryRowRepository categoryRowRepository;

    public Map<Long, List<String>> getAllCategoryRow() {
        List<CategoryRow> allCategoryRows = categoryRowRepository.findAllCategoryRows();

        return allCategoryRows.stream().collect(
                Collectors.groupingBy(
                        CategoryRow::getId,
                        Collectors.mapping(CategoryRow::getCategory, Collectors.toList())
                )
        );
    }

    public List<CategoryRow> getAllCategoryRowByCategoryId(Long categoryId) {
        return categoryRowRepository.findCategoryRowByCategoryId(categoryId);
    }
}
