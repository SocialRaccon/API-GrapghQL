package itst.social_raccoon_api.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "post")
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    @JsonBackReference(value = "user-post")
    private UserModel user;

    private Timestamp dateCreated;
    private String description;
    private String imageUrl;

    @JsonManagedReference(value = "post-comment")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentModel> comments;

    @JsonManagedReference(value = "post-image")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagePostModel> images;

    // Constructores
    public PostModel() {
    }

    public PostModel(String description, String imageUrl, UserModel user, Timestamp dateCreated) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.user = user;
        this.dateCreated = dateCreated;
    }

    // Getters y Setters
    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public List<ImagePostModel> getImages() {
        return images;
    }

    public void setImages(List<ImagePostModel> images) {
        this.images = images;
    }
}