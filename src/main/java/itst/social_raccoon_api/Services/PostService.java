package itst.social_raccoon_api.Services;

import itst.social_raccoon_api.Models.PostModel;
import itst.social_raccoon_api.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository PostRepository;

    public List<PostModel> getAllPosts() {
        return PostRepository.findAll();
    }

    public Optional<PostModel> getPostById(Integer id) {
        return PostRepository.findById(id);
    }

    public Optional<PostModel> Description(Integer id){
        return getPostById(id).map(post -> {
            String nuevaDescription = "";
            post.setPostDescription(nuevaDescription);
            return savePost(post);
        });
    }

    public PostModel savePost(PostModel post) {
        return PostRepository.save(post);
    }

    public Optional<PostModel> actualizarUrlImagen(Integer id, String nuevaUrlImagen) {
        return getPostById(id).map(post -> {
            post.setImages(nuevaUrlImagen);
            return savePost(post);
        });
    }

    public void deletePostById(Integer id) {
        PostRepository.deleteById(id);
    }

    public PostModel updatePost(Integer id, PostModel postDetails) {
        Optional<PostModel> post = PostRepository.findById(id);
        if (post.isPresent()) {
            PostModel existingPost = post.get();
            existingPost.setPostDescription(postDetails.getPostDescription());
            existingPost.setImages(postDetails.getImages());
            existingPost.setDateCreated(postDetails.getDateCreated());
            return PostRepository.save(existingPost);
        }
        return null;
    }

    public Optional<PostModel> findByUser(Integer userId) {
        return PostRepository.findById(userId);
    }

    public void deletePost(Integer id) {

    }
}