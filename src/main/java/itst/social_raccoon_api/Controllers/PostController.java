package itst.social_raccoon_api.Controllers;

import itst.social_raccoon_api.Models.PostModel;
import itst.social_raccoon_api.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/posts", "post"})

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Post", description = "Provide methods to manage posts")
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
    public ResponseEntity<PostModel> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/descripcion/{id}")
    public ResponseEntity<String> obtenerDescriptionPost(@PathVariable Integer id) {
        return postService.getPostById(id)
                .map(PostModel::getDescription)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/imagen/{id}")
    public ResponseEntity<String> obtenerUrlImagenPost(@PathVariable Integer id) {
        return postService.getPostById(id)
                .map(PostModel::getImageUrl)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/descripcion/{id}")
    public ResponseEntity<PostModel> actualizarDescriptionPost(@PathVariable Integer id, @RequestBody String nuevaDescripcion) {
        Optional<PostModel> postOptional = postService.getPostById(id);

        if (postOptional.isPresent()) {
            PostModel post = postOptional.get();
            post.setDescription(nuevaDescripcion);
            postService.savePost(post);
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
