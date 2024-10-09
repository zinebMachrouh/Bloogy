package dao.Interfaces;

import models.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagDAO {
    public Tag addTag(Tag tag) throws SQLException;
    public Tag updateTag(Tag tag) throws SQLException;
    void deleteTag(int id) throws SQLException;
    public List<Tag> getAllTags() throws SQLException;
    public Tag getTagById(int id) throws SQLException;
    public int getTotalCount() throws SQLException;
}
