package com.shad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shad.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
