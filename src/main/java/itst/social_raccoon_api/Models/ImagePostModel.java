package itst.social_raccoon_api.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "image_post")
public class ImagePostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagePost;
    private String url;
    private String thumbnail;
    private Integer idPost;

    public ImagePostModel() {
    }
    public ImagePostModel(Integer idImagePost, String url, String thumbnail, Integer idPost) {
        this.idImagePost = idImagePost;
        this.url = url;
        this.thumbnail = thumbnail;
        this.idPost = idPost;
    }
    public Integer getIdImagePost() {
        return idImagePost;
    }

    public void setIdImagePost(Integer idImagePost) {
        this.idImagePost = idImagePost;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    @Override
    public String toString() {
        return "ImagePost{" +
                "idImagePost=" + idImagePost +
                ", url='" + url + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", idPost=" + idPost +
                '}';
    }
    @Entity(name = "image_post")
    public static class imagePostModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idImagePost;
        private String url;
        private String thumbnail;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "idPost")
        @JsonBackReference(value = "post-image")
        private PostModel post;
        

        public PostModel getPost() {
            return post;
        }

        public void setPost(PostModel post) {
            this.post = post;
        }
    }
}
