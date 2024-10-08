package dao.Interfaces;

import models.Category;

import java.sql.SQLException;
import java.util.List;


public interface CategoryDAO {
    public Category addCategory(Category category) throws SQLException;
    public Category updateCategory(Category category) throws SQLException;
    void deleteCategory(int id) throws SQLException;
    public List<Category> getAllCategories() throws SQLException;
    public Category getCategoryById(int id) throws SQLException;
    public int getTotalCount() throws SQLException;
}
