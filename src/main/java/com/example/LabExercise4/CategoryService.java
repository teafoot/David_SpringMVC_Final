package com.example.LabExercise4;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
//    private static final List<Category> categories = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();

//    static {
//        categories.add(new Category("SS", "School Supplies"));
//        categories.add(new Category("Dr", "Drinks"));
//    }

    public CategoryService() {
        categories.add(new Category("SS", "School Supplies"));
        categories.add(new Category("Dr", "Drinks"));
    }

    public List<Category> retrieveCategories() {
        return new ArrayList<>(categories);
    }

    public Category retrieveCategory(String catCode) {
        for (Category category: categories) {
            if (category.getCatCode().equals(catCode)) {
                return category;
            }
        }
        return null;
    }

    public void addCategory(String catCode, String catDesc) {
        categories.add(new Category(catCode, catDesc));
    }

    public void updateCategory(Category cat) {
        for (Category category: categories) {
            if (category.getCatCode().equals(cat.getCatCode())) {
                category.setCatDesc(cat.getCatDesc());
                break;
            }
        }
//        deleteCategory(category.getCatCode());
//        addCategory(category.getCatCode(), category.getCatDesc());
    }

    public void deleteCategory(String catCode) {
        for (int i = 0; i < categories.size(); i++) {
            if (catCode.equals(categories.get(i).getCatCode())) {
                categories.remove(i);
                break;
            }
        }
    }
}
