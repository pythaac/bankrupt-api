package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryResourceService {
    private final CategoryRepository categoryRepository;


}
