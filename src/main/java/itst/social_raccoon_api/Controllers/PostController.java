package itst.social_raccoon_api.Controllers;

public class Postcontroller {
    package itst.social_raccoon_api.Controllers;

import itst.social_raccoon_api.Models.PostModel;
import itst.social_raccoon_api.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<PostModel> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostModel> getPostById(@PathVariable int id) {
        Optional<PostModel> post = postRepository.findById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PostModel> createPost(@RequestBody PostModel post) {
        PostModel savedPost = postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostModel> updatePost(@PathVariable int id, @RequestBody PostModel post) {
        if (!postRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        post.setIdPost(id);
        PostModel updatedPost = postRepository.save(post);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        if (!postRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        postRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
}
