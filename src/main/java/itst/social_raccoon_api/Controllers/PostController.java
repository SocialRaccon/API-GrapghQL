package itst.social_raccoon_api.Controllers;

import itst.social_raccoon_api.Models.PostModel;
import itst.social_raccoon_api.Services.PostService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")

public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostModel> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostModel> getPostById(@PathVariable Integer id) {
        Optional<PostModel> post = postService.getPostById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PostModel createPost(@RequestBody PostModel post) {
        return postService.savePost(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostModel> updatePost(@PathVariable Integer id, @RequestBody PostModel postDetails) {
        PostModel updatedPost = postService.updatePost(id, postDetails);
        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

}
