package dto;

import models.Category;

import java.util.Objects;

public class CategoryDTO {
    private int id;
    private String name;
    private String description;

    public CategoryDTO() {
    }

    public CategoryDTO(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    public Category dtoToCategory() {
        return new Category(id, name, description);
    }

    public static CategoryDTO categoryToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }
}
