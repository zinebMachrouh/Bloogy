package services.Interfaces;

import dto.TagDTO;
import models.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagService {
    public Tag addTag(TagDTO tag) throws SQLException;
    public Tag updateTag(TagDTO tag) throws SQLException;
    void deleteTag(int id) throws SQLException;
    public List<Tag> getAllTags() throws SQLException;
    public Tag getTagById(int id) throws SQLException;
    public int getTotalCount() throws SQLException;
}
