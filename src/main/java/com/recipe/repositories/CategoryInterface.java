package com.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.recipe.domain.Category;

public interface CategoryInterface extends CrudRepository<Category, Long> {

}
