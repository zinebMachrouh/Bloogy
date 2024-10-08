package dto;

import models.Tag;

public class TagDTO {
    private int id;
    private String name;

    public TagDTO() {

    }

    public TagDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagDTO tagDTO = (TagDTO) o;

        if (id != tagDTO.id) return false;
        return name != null ? name.equals(tagDTO.name) : tagDTO.name == null;
    }

    public Tag dtoToModel() {
        return new Tag(id, name);
    }

    public static TagDTO modelToDTO(Tag tag) {
        return new TagDTO(tag.getId(), tag.getName());
    }
}
