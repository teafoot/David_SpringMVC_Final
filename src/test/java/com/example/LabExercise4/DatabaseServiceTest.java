package com.example.LabExercise4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

// The tests will not affect the DB
@RunWith(MockitoJUnitRunner.class)
class DatabaseServiceTest {
    @InjectMocks
    private DatabaseService databaseService;

    @Mock
    private java.sql.Connection javaConnectionMock;
    @Mock
    private ResultSet resultSetMock;
    @Mock
    private Connection123 daoConnectionMock;
    @Mock
    private PreparedStatement preparedStatementMock;

    private Category category;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.openMocks(this);
        Mockito.when(daoConnectionMock.connect()).thenReturn(javaConnectionMock);
        Mockito.when(javaConnectionMock.prepareStatement(any(String.class))).thenReturn(preparedStatementMock);// "insert into category values (?, ?)" ; "select * from category where catcode=?"

        category = new Category("101","Foodly");
    }

    @Test
    void addCategory() throws SQLException {
        databaseService.addCategory(category); // the call
        Mockito.verify(preparedStatementMock).executeUpdate(); // verify method called
    }

    @Test
    void editCategory() throws SQLException {
        Mockito.when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        Mockito.when(resultSetMock.first()).thenReturn(true);
        Mockito.when(resultSetMock.getString("catcode")).thenReturn("101");
        Mockito.when(resultSetMock.getString("catdesc")).thenReturn("Foodly");
        // search
        Category searchedCategory = databaseService.search("101"); // the call
        Mockito.verify(preparedStatementMock).executeQuery(); // verify method called
        assertEquals("Foodly", searchedCategory.getCatDesc());

        // edit
        category = new Category("101","Foodly and Bevly");
        Category editedCategory =  databaseService.editCategory(category,"101"); // the call
        Mockito.verify(preparedStatementMock).executeUpdate(); // verify method called
        assertEquals("Foodly and Bevly", editedCategory.getCatDesc());
    }

    @Test
    void deleteCategory() throws SQLException {
        databaseService.deleteCategory(category.getCatCode()); // the call
        Mockito.verify(preparedStatementMock).executeUpdate(); // verify method called
    }

    @Test
    void search() throws SQLException {
        Mockito.when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        Mockito.when(resultSetMock.first()).thenReturn(true);
        Mockito.when(resultSetMock.getString("catcode")).thenReturn("101");
        Mockito.when(resultSetMock.getString("catdesc")).thenReturn("Foodly");

        Category searchedCategory = databaseService.search("101"); // the call
        Mockito.verify(preparedStatementMock).executeQuery(); // verify method called
        assertEquals("101", searchedCategory.getCatCode());
        assertEquals("Foodly", searchedCategory.getCatDesc());
    }

    @Test
    void searchNoRecord() throws SQLException {
        Mockito.when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        Mockito.when(resultSetMock.first()).thenReturn(false);

        Category searchedCategory = databaseService.search("not-existing-record"); // the call
        Mockito.verify(preparedStatementMock).executeQuery(); // verify method called
        assertNull(searchedCategory);
    }

    @Test
    void getCategories() throws SQLException {
        Mockito.doReturn(resultSetMock).when(preparedStatementMock).executeQuery();
        Mockito.when(resultSetMock.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false); // must end with false or else will loop indefinitely

        List<Category> categories = databaseService.getCategories(); // the call
        Mockito.verify(preparedStatementMock).executeQuery(); // verify method called
        Mockito.verify(resultSetMock, Mockito.times(4)).getString(any(String.class)); // verify method called
        Mockito.verify(resultSetMock, Mockito.times(2)).getString("catcode"); // verify method called
        Mockito.verify(resultSetMock, Mockito.times(2)).getString("catdesc"); // verify method called
        assertEquals(2, categories.size());
    }

    @Test
    void getItems() throws SQLException, ClassNotFoundException {
        Mockito.doReturn(resultSetMock).when(preparedStatementMock).executeQuery();
        Mockito.when(resultSetMock.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false); // must end with false or else will loop indefinitely

        List<Item> items = databaseService.getItems("whatever"); // the call
        Mockito.verify(preparedStatementMock).executeQuery(); // verify method called
        Mockito.verify(resultSetMock, Mockito.times(4)).getString(any(String.class)); // verify method called
        Mockito.verify(resultSetMock, Mockito.times(2)).getString("itemcode"); // verify method called
        Mockito.verify(resultSetMock, Mockito.times(2)).getString("itemdesc"); // verify method called
        assertEquals(2, items.size());
    }
}