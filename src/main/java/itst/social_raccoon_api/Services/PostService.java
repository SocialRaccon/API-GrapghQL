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
    private PostRepository postRepository;

    public List<PostModel> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<PostModel> getPostById(Integer id) {
        return postRepository.findById(id);
    }

    public Optional<PostModel> Description(Integer id){
        return getPostById(id).map(post -> {
            String nuevaDescription = "";
            post.setPostDescription(nuevaDescription);
            return savePost(post);
        });
    }

    public PostModel savePost(PostModel post) {
        return postRepository.save(post);
    }

    public Optional<PostModel> actualizarUrlImagen(Integer id, String nuevaUrlImagen) {
        return getPostById(id).map(post -> {
            post.setImages(nuevaUrlImagen);
            return savePost(post);
        });
    }

    public void deletePostById(Integer id) {
        postRepository.deleteById(id);
    }

    public PostModel updatePost(Integer id, PostModel postDetails) {
        Optional<PostModel> post = postRepository.findById(id);
        if (post.isPresent()) {
            PostModel existingPost = post.get();
            existingPost.setPostDescription(postDetails.getPostDescription());
            existingPost.setImages(postDetails.getImages());
            existingPost.setDateCreated(postDetails.getDateCreated());
            return postRepository.save(existingPost);
        }
        return null;
    }

    public Optional<PostModel> findByUser(Integer userId) {
        return postRepository.findById(userId);
    }

    public void deletePost(Integer id) {

    }
}