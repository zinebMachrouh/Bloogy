package services;

import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CommentDAO;
import dto.ArticleDTO;
import dto.CategoryDTO;
import dto.CommentDTO;
import dto.UserDTO;
import models.Category;
import models.Comment;
import models.Article;
import models.User;
import models.enums.ArticleStatus;
import models.enums.CommentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {
    @Mock
    private CommentDAO commentDAO;

    @Mock
    private ArticleDAO articleDAO;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddComment() throws SQLException {
        ArticleDTO articleDTO = new ArticleDTO(1, "Article Title", "Article Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new CategoryDTO(1, "Category 1", "Description 1"), new UserDTO(1,"user 01", "user@gmail.com", "1234"));
        Article article = new Article(1, "Article Title", "Article Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1"));
        CommentDTO commentDTO = new CommentDTO(1, "Sample Comment", new Date(), articleDTO, new UserDTO(2,"user 02", "user02@gmail.com", "1234"));
        Comment comment = new Comment(1, "Sample Comment", new Date(), CommentStatus.approved, article, new User(3,"user 01", "User3@gmail.com", "1234"));

        when(commentDAO.addComment(any(Comment.class))).thenReturn(comment);

        Comment result = commentService.addComment(commentDTO);

        assertNotNull(result);
        assertEquals("Sample Comment", result.getContent());
        verify(commentDAO, times(1)).addComment(any(Comment.class));
    }

    @Test
    void testUpdateComment() throws SQLException {
        Article article = new Article(1, "Article Title", "Article Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1"), new User(1,"user 01", "user@gmail.com", "1234"));
        ArticleDTO articleDTO = new ArticleDTO(1, "Article Title", "Article Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new CategoryDTO(1, "Category 1", "Description 1"), new UserDTO(2,"user 01", "use2r@gmail.com", "1234"));
        UserDTO user = new UserDTO(4,"user 01", "user01@gmail.com", "1234");
        CommentDTO commentDTO = new CommentDTO(1, "Updated Comment", new Date(), articleDTO, user );
        Comment comment = new Comment(1, "Updated Comment", new Date(), CommentStatus.approved, article, new User(3,"user 01", "User@gmail.com", "1234"));

        when(commentDAO.getCommentById(1)).thenReturn(comment);
        when(commentDAO.updateComment(any(Comment.class))).thenReturn(comment);

        Comment result = commentService.updateComment(commentDTO);

        assertNotNull(result);
        assertEquals("Updated Comment", result.getContent());
        verify(commentDAO, times(1)).getCommentById(1);
        verify(commentDAO, times(1)).updateComment(any(Comment.class));
    }

    @Test
    void testDeleteComment() throws SQLException {
        Comment comment = new Comment(1, "Sample Comment", new Date(), CommentStatus.approved, new Article(1, "Article Title", "Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1")), new User("user 01", "User@gmail.com", "1234"));

        when(commentDAO.getCommentById(1)).thenReturn(comment);

        commentService.deleteComment(1);

        verify(commentDAO, times(1)).deleteComment(1);
    }


    @Test
    void testGetCommentById() throws SQLException {
        Comment comment = new Comment(1, "Sample Comment", new Date(), CommentStatus.approved, new Article(1, "Article Title", "Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1")), new User("user 01", "User@gmail.com", "1234"));


        when(commentDAO.getCommentById(1)).thenReturn(comment);

        Comment result = commentService.getCommentById(1);

        assertNotNull(result);
        assertEquals("Sample Comment", result.getContent());

        verify(commentDAO, times(1)).getCommentById(1);
    }


    @Test
    void testGetAllComments() throws SQLException {
        Comment comment1 = new Comment(1, "Comment 1", new Date(), CommentStatus.approved, new Article(1, "Article Title", "Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1")), new User("user 01", "User@gmail.com", "1234"));
        Comment comment2 = new Comment(2, "Comment 2", new Date(), CommentStatus.approved, new Article(1, "Article Title", "Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1")), new User("user 02", "User@gmail.com", "1234"));
        List<Comment> commentList = Arrays.asList(comment1, comment2);

        when(articleDAO.getArticleById(1)).thenReturn(new Article());
        when(commentDAO.getAllComments(1)).thenReturn(commentList);

        List<Comment> result = commentService.getAllComments(1);

        assertEquals(2, result.size());
        assertEquals("Comment 1", result.get(0).getContent());
        assertEquals("Comment 2", result.get(1).getContent());
        verify(articleDAO, times(1)).getArticleById(1);
        verify(commentDAO, times(1)).getAllComments(1);
    }

    @Test
    void testGetAllCommentsArticleNotFound() throws SQLException {
        when(articleDAO.getArticleById(1)).thenReturn(null);

        List<Comment> result = commentService.getAllComments(1);

        assertNull(result);
        verify(articleDAO, times(1)).getArticleById(1);
        verify(commentDAO, never()).getAllComments(1);
    }
}
