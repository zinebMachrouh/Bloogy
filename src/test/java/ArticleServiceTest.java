import dao.Interfaces.ArticleDAO;
import dto.ArticleDTO;
import dto.CategoryDTO;
import models.Article;
import models.Category;
import models.enums.ArticleStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.ArticleServiceImpl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleServiceImplTest {

    @Mock
    private ArticleDAO articleDAO;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddArticle() throws SQLException {
        // Arrange
        ArticleDTO articleDTO = new ArticleDTO(1, "Article Title", "Article Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new CategoryDTO(1, "Category 1", "Description 1"));
        Article article = new Article(1, "Article Title", "Article Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1"));

        when(articleDAO.addArticle(any(Article.class))).thenReturn(article);

        // Act
        Article result = articleService.addArticle(articleDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Article Title", result.getTitle());
        assertEquals("Article Content", result.getContent());
        verify(articleDAO, times(1)).addArticle(any(Article.class));
    }

    @Test
    void testUpdateArticle() throws SQLException {
        // Arrange
        ArticleDTO articleDTO = new ArticleDTO(1, "Updated Title", "Updated Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new CategoryDTO(1, "Category 1", "Description 1"));
        Article article = new Article(1, "Updated Title", "Updated Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1"));

        when(articleDAO.getArticleById(1)).thenReturn(article);
        when(articleDAO.updateArticle(any(Article.class))).thenReturn(article);

        // Act
        Article result = articleService.updateArticle(articleDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        verify(articleDAO, times(1)).getArticleById(1);
        verify(articleDAO, times(1)).updateArticle(any(Article.class));
    }

    @Test
    void testUpdateArticleNotFound() throws SQLException {
        // Arrange
        ArticleDTO articleDTO = new ArticleDTO(1, "Updated Title", "Updated Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new CategoryDTO(1, "Category 1", "Description 1"));

        when(articleDAO.getArticleById(1)).thenReturn(null);

        // Act
        Article result = articleService.updateArticle(articleDTO);

        // Assert
        assertNull(result);
        verify(articleDAO, times(1)).getArticleById(1);
        verify(articleDAO, never()).updateArticle(any(Article.class));
    }

    @Test
    void testDeleteArticle() throws SQLException {
        // Arrange
        Article article = new Article(1, "Article Title", "Article Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1"));
        when(articleDAO.getArticleById(1)).thenReturn(article);

        // Act
        articleService.deleteArticle(1);

        // Assert
        verify(articleDAO, times(1)).deleteArticle(1);
    }

    @Test
    void testDeleteArticleNotFound() throws SQLException {
        // Arrange
        when(articleDAO.getArticleById(1)).thenReturn(null);

        // Act & Assert
        assertThrows(SQLException.class, () -> articleService.deleteArticle(1));
        verify(articleDAO, never()).deleteArticle(1);
    }

    @Test
    void testGetAllArticles() throws SQLException {
        // Arrange
        Article article1 = new Article(1, "Article 1", "Content 1", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1"));
        Article article2 = new Article(2, "Article 2", "Content 2", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(2, "Category 2", "Description 2"));
        List<Article> articleList = Arrays.asList(article1, article2);

        when(articleDAO.getAllArticles()).thenReturn(articleList);

        // Act
        List<ArticleDTO> result = articleService.getAllArticles();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Article 1", result.get(0).getTitle());
        assertEquals("Article 2", result.get(1).getTitle());
        verify(articleDAO, times(1)).getAllArticles();
    }

    @Test
    void testGetArticleById() throws SQLException {
        // Arrange
        Article article = new Article(1, "Article Title", "Article Content", new Date(), new Date(), ArticleStatus.PUBLISHED, new Category(1, "Category 1", "Description 1"));

        when(articleDAO.getArticleById(1)).thenReturn(article);

        // Act
        Article result = articleService.getArticleById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Article Title", result.getTitle());
        verify(articleDAO, times(1)).getArticleById(1);
    }

    @Test
    void testGetArticleByIdNotFound() throws SQLException {
        // Arrange
        when(articleDAO.getArticleById(1)).thenReturn(null);

        // Act
        Article result = articleService.getArticleById(1);

        // Assert
        assertNull(result);
        verify(articleDAO, times(1)).getArticleById(1);
    }
}
