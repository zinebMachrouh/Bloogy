package services.Interfaces;

import dto.CategoryDTO;
import models.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    public Category addCategory(CategoryDTO category) throws SQLException;
    public Category updateCategory(CategoryDTO category) throws SQLException;
    void deleteCategory(int id) throws SQLException;
    List<Category> getAllCategories() throws SQLException;
    Category getCategoryById(int id) throws SQLException;
}
