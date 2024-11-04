package com.bankrupt.bankruptapi.repository;

import com.bankrupt.bankruptapi.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
