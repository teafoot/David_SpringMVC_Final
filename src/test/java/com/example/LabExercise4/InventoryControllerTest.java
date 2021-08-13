package com.example.LabExercise4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

// don't test CategoryService|DatabaseService in this level
// only test InventoryController
@RunWith(MockitoJUnitRunner.class)
class InventoryControllerTest {
    @InjectMocks
    InventoryController inventoryController;

    @Mock
    DatabaseService databaseService;

    @Mock
    ModelMap model;

    @Mock
    List<Item> itemsMock;
    @Mock
    Category categoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listCategories() throws Exception {
        List<Category> categoriesTest = new ArrayList<>();
        categoriesTest.add(new Category("100", "100DESC"));
        categoriesTest.add(new Category("101", "101DESC"));
        Mockito.when(databaseService.getCategories()).thenReturn(categoriesTest);

        assertEquals("category-list", inventoryController.listCategories(model)); // the call
        Mockito.verify(databaseService).getCategories(); // verify method called
        Mockito.verify(model).addAttribute(eq("categories"), anyListOf(Category.class)); // verify method called
    }

    @Test
    void addCategoryPage() {
        assertEquals("category-add", inventoryController.addCategoryPage());
    }

    @Test
    void addCategory() throws SQLException {
        Mockito.when(databaseService.search("101")).thenReturn(null);
        assertEquals("redirect:/", inventoryController.addCategory(model, "101", "101DESC")); // the call
        Mockito.verify(databaseService).addCategory(any(Category.class)); // verify method called
    }

    @Test
    void updateCategoryPage() throws SQLException {
        Mockito.when(databaseService.search("101")).thenReturn(categoryMock);
        assertEquals("category-edit", inventoryController.updateCategoryPage(model, "101"));
        Mockito.verify(model).addAttribute(eq("category"), any(Category.class)); // verify method called
    }

    @Test
    void updateCategory() throws SQLException {
        assertEquals("redirect:/", inventoryController.updateCategory(model, "101", "101DESC")); // the call
        Mockito.verify(databaseService).editCategory(any(Category.class), eq("101")); // verify method called
    }

    @Test
    void deleteCategory() throws SQLException {
        assertEquals("redirect:/", inventoryController.deleteCategory(model, "101")); // the call
        Mockito.verify(databaseService).deleteCategory("101"); // verify method called
    }

    @Test
    void listCategoryItems() throws SQLException, ClassNotFoundException {
        Mockito.when(databaseService.getItems("101")).thenReturn(itemsMock);
        Mockito.when(itemsMock.size()).thenReturn(1);

        assertEquals("item-list", inventoryController.listCategoryItems(model, "101")); // the call
        Mockito.verify(model).addAttribute(eq("items"), anyListOf(Item.class)); // verify method called
    }
}

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(DatabaseService.class) // for instantiating DatabaseService
//    @Test
//    void listCategories2() throws Exception {
//        PowerMockito.whenNew(DatabaseService.class).withArguments(connection123.connect()).thenReturn(databaseService);
//        PowerMockito.verifyNew(DatabaseService.class).withArguments(connection123.connect());
//        Mockito.doAnswer(resultCaptor).when(databaseService).getCategories();// capture the return values from loginService.validateUser
//        DatabaseService classUnderTest = PowerMockito.spy(new DatabaseService(connection123.connect()));
//        PowerMockito.whenNew(DatabaseService.class).withArguments(connection123.connect()).thenReturn(classUnderTest);
////        Mockito.when(classUnderTest.getCategories()).thenReturn(categoriesMock);
//        Mockito.when(javaConnectionMock.prepareStatement(anyString())).thenReturn(mock(PreparedStatement.class));
//        Mockito.doReturn(mock(PreparedStatement.class)).when(javaConnectionMock).prepareStatement(anyString());
//        Mockito.doReturn(categoriesMock).when(databaseService).getCategories();
//        assertEquals("category-list", inventoryController.listCategories(model)); // the call
//        assertTrue(resultCaptor.getResult());
//        PowerMockito.verifyNew(DatabaseService.class).withArguments(connection123.connect());
//        Mockito.verify(databaseService).getCategories(); // verify method called
//    }