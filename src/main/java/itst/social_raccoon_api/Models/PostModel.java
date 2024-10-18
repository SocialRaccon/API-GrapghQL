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

    private String user;
    private Timestamp dateCreated;
    private String description;
    private String imageUrl;

    @JsonManagedReference(value = "post-comment")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentModel> comments;

    public PostModel() {
    }

    public PostModel(String description, String imageUrl, String user, Timestamp dateCreated) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.user = user;
        this.dateCreated = dateCreated;
    }

    public Integer getPost() {
        return idPost;
    }

    public void setPost(Integer idPost) {
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

    public String getUser() {
        return user;
    }

    @Entity
    public class ImagePost {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String imageUrl;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Post getPost() {
            return post;
        }

        public void setPost(Post post) {
            this.post = post;
        }

        @ManyToOne
        @JoinColumn(name = "post_id")
        private Post post;


    }
    @Entity
    public class Post {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        @OneToOne(cascade = CascadeType.ALL, mappedBy = "post")
        private Description description;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Description getDescription() {
            return description;
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public List<ImagePost> getImages() {
            return images;
        }

        public void setImages(List<ImagePost> images) {
            this.images = images;
        }

        @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<ImagePost> images;


    }

    @Entity
    public class Description {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String content;

        @OneToOne
        @JoinColumn(name = "post_id")
        private Post post;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Post getPost() {
            return post;
        }

        public void setPost(Post post) {
            this.post = post;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
    public void setUser(String idUser) {
        this.user = idUser;
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
                ", idUser=" + user +
                ", dateCreated=" + dateCreated +
                '}';
    }

}
