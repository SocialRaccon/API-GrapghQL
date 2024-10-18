package itst.social_raccoon_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import itst.social_raccoon_api.Controllers.PostController;
import itst.social_raccoon_api.Models.PostModel;
import itst.social_raccoon_api.Services.PostService;
import org.hamcrest.internal.SelfDescribingValueIterator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.awt.*;
import java.lang.ref.Cleaner;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest(PostController.class)
public class SocialRacoonapiApplicationTests {

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
	public void getPostByIdTest() throws Exception {
		PostModel post = new PostModel("Test Post", "http://example.com/image.jpg", "user1", Timestamp.from(Instant.now()));
		post.setPost(1);

		when(postService.getPostById(1)).thenReturn(Optional.of(post));

		mockMvc.perform(get("/api/posts/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.post", is(1)))
				.andExpect(jsonPath("$.description", is("Test Post")));
	}

	@Test
	public void deletePostTest() throws Exception {
		mockMvc.perform(delete("/api/posts/1"))
				.andExpect(status().isOk());
	}
}