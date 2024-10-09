package services;

import dao.Interfaces.TagDAO;
import dto.TagDTO;
import models.Tag;
import services.Interfaces.TagService;

import java.sql.SQLException;
import java.util.List;

public class TagServiceImpl implements TagService {
    private final TagDAO tagDAO;

    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public Tag addTag(TagDTO tag) throws SQLException {
        Tag tagModel = tag.dtoToModel();
        return tagDAO.addTag(tagModel);
    }

    @Override
    public Tag updateTag(TagDTO tag) throws SQLException {
        Tag tagModel = tag.dtoToModel();
        return tagDAO.updateTag(tagModel);
    }

    @Override
    public void deleteTag(int id) throws SQLException {
        if (tagDAO.getTagById(id) != null) {
            tagDAO.deleteTag(id);
        }
    }

    @Override
    public Tag getTagById(int id) throws SQLException {
        if (tagDAO.getTagById(id) != null) {
            return tagDAO.getTagById(id);
        } else {
            return null;
        }
    }

    @Override
    public int getTotalCount() throws SQLException {
        return tagDAO.getTotalCount();
    }

    @Override
    public List<Tag> getAllTags() throws SQLException {
        return tagDAO.getAllTags();
    }
}
