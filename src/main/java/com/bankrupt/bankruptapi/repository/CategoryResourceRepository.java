package com.bankrupt.bankruptapi.repository;

import com.bankrupt.bankruptapi.dao.CategoryResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryResourceRepository extends JpaRepository<CategoryResource, Long> {

    List<CategoryResource> findAllByCategoryId(Long categoryId);
    void deleteAllByCategoryId(Long categoryId);
}
