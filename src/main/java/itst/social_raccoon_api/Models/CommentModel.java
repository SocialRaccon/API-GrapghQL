package itst.social_raccoon_api.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import itst.social_raccoon_api.Models.CompositeKeys.CommentPK;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "comment")
@IdClass(CommentPK.class)
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComment;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    @JsonBackReference(value = "user-comment")
    private UserModel user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPost")
    @JsonBackReference(value = "post-comment")
    private PostModel post;

    private String comment;
    private Timestamp date;

    public CommentModel(String comment, UserModel user, Timestamp date, PostModel post) {
        this.comment = comment;
        this.user = user;
        this.date = date;
        this.post = post;
    }

    public CommentModel() {

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel idUser) {
        this.user = idUser;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel idPost) {
        this.post = idPost;
    }
}
