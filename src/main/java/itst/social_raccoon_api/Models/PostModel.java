package itst.social_raccoon_api.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.mapping.Value;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
public class PostModel {
    @OneToOne(mappedBy = "post")
    private PostDescriptionModel
    postDescription;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPost")
    @JsonBackReference(value = "user-post")
    private List<ImagePostModel> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idUser")
    @JsonManagedReference
    private UserModel user;

    private Timestamp dateCreated;

    @JsonManagedReference(value = "post-description")
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private PostDescriptionModel PostDescription;

    @JsonManagedReference(value = "post-comment")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentModel> comments;

    public void setImages(String nuevaUrlImagen) {

    }

    public void setPostDescription(String nuevaDescription) {

    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
    // Getters y Setters
    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public List<ImagePostModel> getImages() {
        return images;
    }

    public void setImages(List<ImagePostModel> images) {
        this.images = images;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public PostDescriptionModel getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(PostDescriptionModel postDescription) {
        this.postDescription = postDescription;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }
}