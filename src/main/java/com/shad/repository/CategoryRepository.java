package com.shad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shad.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByIsActiveTrueAndIsDeletedFalse();

    Optional<Category> findByIdAndIsDeletedFalse(Integer id);

    List<Category> findByIsDeletedFalse();
}
