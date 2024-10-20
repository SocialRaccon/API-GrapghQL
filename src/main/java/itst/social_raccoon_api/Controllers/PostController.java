package itst.social_raccoon_api.Controllers;

import itst.social_raccoon_api.Models.PostModel;
import itst.social_raccoon_api.Models.PostDescriptionModel;
import itst.social_raccoon_api.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{idPost}/description")
    public String obtenerDescriptionPost(@PathVariable Integer idPost) {
        PostModel post = postRepository.findById(idPost)
                .orElseThrow(() -> new PostModel.ResourceNotFoundException("Post no encontrado"));

        PostDescriptionModel postDescription = post.getPostDescription();
        return postDescription != null ? postDescription.getDescription() : "Descripción no disponible";
    }

    @PutMapping("/{idPost}/description")
    public PostDescriptionModel actualizarDescriptionPost(@PathVariable Integer idPost, @RequestBody String nuevaDescripcion) {
        PostModel post = postRepository.findById(idPost)
                .orElseThrow(() -> new PostModel.ResourceNotFoundException("Post no encontrado"));

        PostDescriptionModel postDescription = post.getPostDescription();
        if (postDescription != null) {
            postDescription.setDescription(nuevaDescripcion);
        } else {
            postDescription = new PostDescriptionModel();
            postDescription.setIdPost(post);
            postDescription.setDescription(nuevaDescripcion);
            post.setPostDescription(postDescription);
        }
        postRepository.save(post);
        return postDescription;
    }
}