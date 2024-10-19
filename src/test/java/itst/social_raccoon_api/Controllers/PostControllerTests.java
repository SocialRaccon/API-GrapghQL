package itst.social_raccoon_api.Controllers;

import itst.social_raccoon_api.Models.PostModel;
import itst.social_raccoon_api.Models.UserModel;
import itst.social_raccoon_api.Services.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PostController.class)
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllPostsTest() throws Exception {
        PostModel post1 = new PostModel("Test Post 1", "http://example.com/image1.jpg", "user1", Timestamp.from(Instant.now()));
        PostModel post2 = new PostModel("Test Post 2", "http://example.com/image2.jpg", "user2", Timestamp.from(Instant.now()));
        post1.setPost(1);
        post2.setPost(2);

        when(postService.getAllPosts()).thenReturn(Arrays.asList(post1, post2));

        mockMvc.perform(get("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].post", is(1)))
                .andExpect(jsonPath("$[1].post", is(2)));
    }

    @Test
    public void updatePostTest() throws Exception {
        PostModel updatedPost = new PostModel("Updated Post", "http://example.com/updatedimage.jpg", new UserModel(), Timestamp.from(Instant.now())); // Cambié "user1" a un objeto UserModel válido
        updatedPost.setPost(1);
        when(postService.updatePost(eq(1), any(PostModel.class))).thenReturn(updatedPost);
        mockMvc.perform(put("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPost)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.post", is(1)))
                .andExpect(jsonPath("$.description", is("Updated Post")));
    }
    @Test
    public void createPostTest() throws Exception {
        PostModel newPost = new PostModel("New Post", "http://example.com/newimage.jpg", new UserModel(1), Timestamp.from(Instant.now())); // Cambié "user1" a un objeto UserModel válido
        newPost.setPost(1);
        when(postService.savePost(any(PostModel.class))).thenReturn(newPost);
        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPost)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.post", is(1)))
                .andExpect(jsonPath("$.description", is("New Post")));
    }

    @Test
    public void deletePostTest() throws Exception {
        mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isOk());
    }
}