package itst.social_raccoon_api.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "post_descriptions")
@Schema(description = "Model representing a post description")
public class PostDescriptionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPostDescription", nullable = false)
    private Integer idPostDescription;

    @Size(max = 150)
    @NotNull
    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @NotNull
    @OneToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference(value = "post-description")
    private PostModel post;

    public Integer getIdPostDescription() {
        return idPostDescription;
    }

    public void setIdPostDescription(Integer idPostDescription) {
        this.idPostDescription = idPostDescription;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    public @Size(max = 150) @NotNull String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 150) @NotNull String description) {
        this.description = description;
    }
}
