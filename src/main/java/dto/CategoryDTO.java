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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CategoryDTO that = (CategoryDTO) obj;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    public Category dtoToModel() {
        return new Category(id, name, description);
    }

    public static CategoryDTO modelToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }
}
