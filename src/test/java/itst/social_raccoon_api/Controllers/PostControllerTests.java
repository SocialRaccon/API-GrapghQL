package itst.social_raccoon_api.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import itst.social_raccoon_api.Models.PostModel;
import itst.social_raccoon_api.Models.PostDescriptionModel;
import itst.social_raccoon_api.Repositories.PostRepository;

class PostControllerTests {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerDescriptionPost_ExistingDescription() {
        // Arrange
        Integer idPost = 1;
        PostModel post = new PostModel();
        PostDescriptionModel description = new PostDescriptionModel();
        description.setDescription("Test description");
        post.setPostDescription(description);

        when(postRepository.findById(idPost)).thenReturn(Optional.of(post));

        // Act
        String result = postController.obtenerDescriptionPost(idPost);

        // Assert
        assertEquals("Test description", result);
        verify(postRepository).findById(idPost);
    }

    @Test
    void testObtenerDescriptionPost_NoDescription() {
        // Arrange
        Integer idPost = 1;
        PostModel post = new PostModel();

        when(postRepository.findById(idPost)).thenReturn(Optional.of(post));

        // Act
        String result = postController.obtenerDescriptionPost(idPost);

        // Assert
        assertEquals("Descripción no disponible", result);
        verify(postRepository).findById(idPost);
    }

    @Test
    void testObtenerDescriptionPost_PostNotFound() {
        // Arrange
        Integer idPost = 1;

        when(postRepository.findById(idPost)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PostModel.ResourceNotFoundException.class, () -> {
            postController.obtenerDescriptionPost(idPost);
        });
        verify(postRepository).findById(idPost);
    }

    @Test
    void testActualizarDescriptionPost_ExistingDescription() {
        // Arrange
        Integer idPost = 1;
        String nuevaDescripcion = "Updated description";
        PostModel post = new PostModel();
        PostDescriptionModel description = new PostDescriptionModel();
        description.setDescription("Old description");
        post.setPostDescription(description);

        when(postRepository.findById(idPost)).thenReturn(Optional.of(post));
        when(postRepository.save(any(PostModel.class))).thenReturn(post);

        // Act
        PostDescriptionModel result = postController.actualizarDescriptionPost(idPost, nuevaDescripcion);

        // Assert
        assertNotNull(result);
        assertEquals(nuevaDescripcion, result.getDescription());
        verify(postRepository).findById(idPost);
        verify(postRepository).save(post);
    }

    @Test
    void testActualizarDescriptionPost_NewDescription() {
        // Arrange
        Integer idPost = 1;
        String nuevaDescripcion = "New description";
        PostModel post = new PostModel();

        when(postRepository.findById(idPost)).thenReturn(Optional.of(post));
        when(postRepository.save(any(PostModel.class))).thenReturn(post);

        // Act
        PostDescriptionModel result = postController.actualizarDescriptionPost(idPost, nuevaDescripcion);

        // Assert
        assertNotNull(result);
        assertEquals(nuevaDescripcion, result.getDescription());
        assertEquals(post, result.getIdPost());
        verify(postRepository).findById(idPost);
        verify(postRepository).save(post);
    }
}