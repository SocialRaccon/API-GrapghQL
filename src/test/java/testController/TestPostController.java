package testController;

import itst.social_raccoon_api.Controllers.PostController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
i
import static org.assertj.core.api.Assertions.assertThat;

import itst.social_raccoon_api.Models.PostModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPostController {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PostController controller;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllPostsTest() throws Exception {
        mvc.perform(get("/api/posts").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void getPostByIdTest() throws Exception {
        mvc.perform(get("/api/posts/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void getPostByIdNotFoundTest() throws Exception {
        mvc.perform(get("/api/posts/999").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createPostTest() throws Exception {
        PostModel newPost = new PostModel();
        newPost.setTitle("Test Post");
        newPost.setContent("This is a test post content");

        mvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPost)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.title", is("Test Post")))
                .andExpect(jsonPath("$.content", is("This is a test post content")));
    }

    @Test
    public void updatePostTest() throws Exception {
        PostModel updatedPost = new PostModel();
        updatedPost.setTitle("Updated Post");
        updatedPost.setContent("This is an updated post content");

        mvc.perform(put("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPost)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated Post")))
                .andExpect(jsonPath("$.content", is("This is an updated post content")));
    }

    @Test
    public void deletePostTest() throws Exception {
        mvc.perform(delete("/api/posts/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}