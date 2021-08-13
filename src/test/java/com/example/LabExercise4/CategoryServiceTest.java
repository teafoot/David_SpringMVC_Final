package com.example.LabExercise4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService();
    }

    @Test
    void retrieveCategories() {
        List<Category> categoryList = categoryService.retrieveCategories(); // the call
        assertEquals(2, categoryList.size());
        assertEquals("School Supplies", categoryList.get(0).getCatDesc());
    }

    @Test
    void retrieveCategory() {
        Category category = categoryService.retrieveCategory("Dr"); // the call
        assertEquals("Drinks", category.getCatDesc());
    }

    @Test
    void addCategory() {
        categoryService.addCategory("404x", "Category :) not found"); // the call
        assertEquals(3, categoryService.retrieveCategories().size());
    }

    @Test
    void updateCategory() {
        Category category = new Category("Dr", "Foods");
        categoryService.updateCategory(category); // the call
        Category category2 = categoryService.retrieveCategory("Dr");
        assertEquals("Foods", category2.getCatDesc());
    }

    @Test
    void deleteCategory() {
        categoryService.deleteCategory("SS"); // the call
        List<Category> categoryList = categoryService.retrieveCategories();
        assertEquals(1, categoryList.size());
    }
}