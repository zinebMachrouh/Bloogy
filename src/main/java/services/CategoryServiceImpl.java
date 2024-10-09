package services;

import dao.Interfaces.CategoryDAO;
import dto.CategoryDTO;
import models.Category;
import services.Interfaces.CategoryService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        if (categoryDAO == null) {
            throw new IllegalArgumentException("CategoryDAO cannot be null");
        }
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Category addCategory(CategoryDTO categoryDTO) throws SQLException {
        Category category = new Category(
                categoryDTO.getId(),
                categoryDTO.getName(),
                categoryDTO.getDescription()
        );
        return categoryDAO.addCategory(category);
    }

    @Override
    public Category updateCategory(CategoryDTO categoryDTO) throws SQLException {
        if (categoryDAO.getCategoryById(categoryDTO.getId()) == null) {
            return null;
        } else {
            Category category = new Category(
                    categoryDTO.getId(),
                    categoryDTO.getName(),
                    categoryDTO.getDescription()
            );
            return categoryDAO.updateCategory(category);
        }
    }

    @Override
    public void deleteCategory(int id) throws SQLException {
        if (categoryDAO.getCategoryById(id) == null) {
            throw new SQLException("Category not found with ID: " + id);
        }
        categoryDAO.deleteCategory(id);
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws SQLException {
        List<Category> allCategories = categoryDAO.getAllCategories();
        return allCategories.stream()
                .map(CategoryDTO::modelToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryById(int id) throws SQLException {
        Category category = categoryDAO.getCategoryById(id);
        return (category != null) ? category : null;
    }
}
