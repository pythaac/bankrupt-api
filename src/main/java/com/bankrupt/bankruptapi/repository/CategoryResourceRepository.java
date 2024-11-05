package com.bankrupt.bankruptapi.repository;

import com.bankrupt.bankruptapi.dao.CategoryResource;
import com.bankrupt.bankruptapi.dao.CategoryRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryResourceRepository extends JpaRepository<CategoryResource, Long> {

}
