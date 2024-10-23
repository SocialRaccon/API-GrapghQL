package itst.social_raccoon_api.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import io.swagger.v3.oas.annotations.media.Schema;
import itst.social_raccoon_api.Dto.PostDTO;
import jakarta.persistence.*;
import org.hibernate.mapping.Value;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Schema(name = "Post", description = "Post model")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPost")
    @JsonBackReference(value = "user-post")
    private List<ImagePostModel> images;

    @ManyToOne
    @JoinColumn(name = "idUser")
    @JsonManagedReference
    private UserModel user;

    private Timestamp dateCreated;

    @JsonManagedReference(value = "post-description")
    @OneToOne(mappedBy = "idPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private PostDescriptionModel PostDescription;

    @JsonManagedReference(value = "post-comment")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentModel> comments;

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

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public PostDescriptionModel getPostDescription() {
        return PostDescription;
    }

    public void setPostDescription(PostDescriptionModel postDescription) {
        PostDescription = postDescription;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
