import dao.Interfaces.CategoryDAO;
import dto.CategoryDTO;
import models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.CategoryServiceImpl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryDAO categoryDAO;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCategory() throws SQLException {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO(1, "Category 1", "Description 1");
        Category category = new Category(1, "Category 1", "Description 1");

        when(categoryDAO.addCategory(any(Category.class))).thenReturn(category);

        // Act
        Category result = categoryService.addCategory(categoryDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Category 1", result.getName());
        assertEquals("Description 1", result.getDescription());
        verify(categoryDAO, times(1)).addCategory(any(Category.class));
    }

    @Test
    void testUpdateCategory() throws SQLException {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO(1, "Updated Category", "Updated Description");
        Category category = new Category(1, "Updated Category", "Updated Description");

        when(categoryDAO.getCategoryById(1)).thenReturn(category);
        when(categoryDAO.updateCategory(any(Category.class))).thenReturn(category);

        // Act
        Category result = categoryService.updateCategory(categoryDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Category", result.getName());
        verify(categoryDAO, times(1)).getCategoryById(1);
        verify(categoryDAO, times(1)).updateCategory(any(Category.class));
    }

    @Test
    void testUpdateCategoryNotFound() throws SQLException {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO(1, "Updated Category", "Updated Description");

        when(categoryDAO.getCategoryById(1)).thenReturn(null);

        // Act
        Category result = categoryService.updateCategory(categoryDTO);

        // Assert
        assertNull(result);
        verify(categoryDAO, times(1)).getCategoryById(1);
        verify(categoryDAO, never()).updateCategory(any(Category.class));
    }

    @Test
    void testDeleteCategory() throws SQLException {
        // Arrange
        Category category = new Category(1, "Category 1", "Description 1");
        when(categoryDAO.getCategoryById(1)).thenReturn(category);

        // Act
        categoryService.deleteCategory(1);

        // Assert
        verify(categoryDAO, times(1)).deleteCategory(1);
    }

    @Test
    void testDeleteCategoryNotFound() throws SQLException {
        // Arrange
        when(categoryDAO.getCategoryById(1)).thenReturn(null);

        // Act & Assert
        assertThrows(SQLException.class, () -> categoryService.deleteCategory(1));
        verify(categoryDAO, never()).deleteCategory(1);
    }

    @Test
    void testGetAllCategories() throws SQLException {
        // Arrange
        Category category1 = new Category(1, "Category 1", "Description 1");
        Category category2 = new Category(2, "Category 2", "Description 2");
        List<Category> categoryList = Arrays.asList(category1, category2);

        when(categoryDAO.getAllCategories()).thenReturn(categoryList);

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Category 1", result.get(0).getName());
        assertEquals("Category 2", result.get(1).getName());
        verify(categoryDAO, times(1)).getAllCategories();
    }

    @Test
    void testGetCategoryById() throws SQLException {
        // Arrange
        Category category = new Category(1, "Category 1", "Description 1");

        when(categoryDAO.getCategoryById(1)).thenReturn(category);

        // Act
        Category result = categoryService.getCategoryById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Category 1", result.getName());
        verify(categoryDAO, times(1)).getCategoryById(1);
    }

    @Test
    void testGetCategoryByIdNotFound() throws SQLException {
        // Arrange
        when(categoryDAO.getCategoryById(1)).thenReturn(null);

        // Act
        Category result = categoryService.getCategoryById(1);

        // Assert
        assertNull(result);
        verify(categoryDAO, times(1)).getCategoryById(1);
    }
}
