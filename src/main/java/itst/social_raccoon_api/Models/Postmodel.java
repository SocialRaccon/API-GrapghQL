package itst.social_raccoon_api.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Post")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPost;
    private String description;
    private String imageUrl;
    private int idUser;
    private Timestamp dateCreated;

    public PostModel() {
    }

    public PostModel(String description, String imageUrl, Integer idUser, Timestamp dateCreated) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.idUser = idUser;
        this.dateCreated = dateCreated;
    }

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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "idPost=" + idPost +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", idUser=" + idUser +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
