package com.example.LabExercise4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;
import java.util.List;

@SessionAttributes({"errorMessage", "errorMessageSession"}) // for the model
@RequestMapping
@Controller
public class InventoryController {
//    @Autowired
//    CategoryService categoryService; // memory

    DatabaseService databaseService;

    public InventoryController() throws SQLException, ClassNotFoundException {
        Connection123 connection123 = new Connection123();
        databaseService = new DatabaseService(connection123.connect());
    }

    @RequestMapping(value = { "/list-categories"}, method = RequestMethod.GET)
    public String listCategories(ModelMap model) throws ClassNotFoundException, SQLException {
        if (model.get("errorMessageSession") != "") {
            model.put("errorMessage", model.get("errorMessageSession"));
            model.put("errorMessageSession", "");
        } else {
            model.put("errorMessage", "");
        }
//        databaseService = new DatabaseService(connection123.connect());
        List<Category> categories = databaseService.getCategories();
        for (Category category: categories) {
            category.setItems(databaseService.getItems(category.getCatCode()));
        }
        model.addAttribute("categories", categories);

        return "category-list";
    }

    @RequestMapping(value = "/add-category", method = RequestMethod.GET)
    public String addCategoryPage() {
        return "category-add";
    }

    @RequestMapping(value = "/add-category", method = RequestMethod.POST)
    public String addCategory(ModelMap model, @RequestParam String catCode, @RequestParam String catDesc) throws SQLException {
        if (databaseService.search(catCode) != null) {
            model.put("errorMessageSession", "Record already exists.");
            return "redirect:/";
        }
        Category category = new Category(catCode, catDesc);
        databaseService.addCategory(category);
        return "redirect:/";
    }

    @RequestMapping(value = "/update-category", method = RequestMethod.GET)
    public String updateCategoryPage(ModelMap model, @RequestParam(defaultValue = "") String id) throws SQLException {
        Category foundCategory = databaseService.search(id);
        model.addAttribute("category", foundCategory); //
        return "category-edit";
    }

    @RequestMapping(value = "/update-category", method = RequestMethod.POST)
    public String updateCategory(ModelMap model, @RequestParam String catCode, @RequestParam String catDesc) throws SQLException { //get the old catcode
        Category newCategory = new Category(catCode, catDesc);
        databaseService.editCategory(newCategory, catCode);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete-category", method = RequestMethod.GET)
    public String deleteCategory(ModelMap model, @RequestParam String id) throws SQLException {
        databaseService.deleteCategory(id);
        model.clear();
        return "redirect:/";
    }

    @RequestMapping(value = "/view-category-items", method = RequestMethod.GET)
    public String listCategoryItems(ModelMap model, @RequestParam(defaultValue = "") String id) throws SQLException, ClassNotFoundException {
        List<Item> itemList = databaseService.getItems(id);
        if (itemList.size() == 0) {
            model.put("errorMessageSession", "There are no items for this category.");
            return "redirect:/";
        }
        model.put("catCode", id);
        model.addAttribute("items", itemList);
        return "item-list";
    }
}