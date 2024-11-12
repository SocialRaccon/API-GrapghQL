package itst.socialraccoon.api.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Model representing a profile image")
@Entity
@Table(name = "image_profile")
public class ImageProfileModel {

    @Schema(description = "Unique identifier of the image profile", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idImageProfile")
    @JsonProperty("idImageProfile")
    private Integer idImageProfile;

    @ManyToOne()
    @JoinColumn(name = "idProfile", nullable = false)
    @JsonProperty("idProfile")
    @Schema(description = "Profile to which the image belongs", example = "1")
    @JsonBackReference(value = "profile-image")
    private ProfileModel profile;

    @Schema(description = "URL of the image", example = "https://www.example.com/image.jpg")
    @Column(name = "imageUrl", nullable = false, length = 500)
    @Size(max = 500, message = "La URL de la imagen debe tener como máximo 255 caracteres")
    @NotBlank(message = "La URL de la imagen no puede estar vacía y debe contener al menos un carácter que no sea un espacio en blanco")
    @Pattern(regexp = "^(http|https)://.*\\.(jpg|jpeg|png)$",
            message = "La URL de la imagen debe ser una URL de imagen válida que termine en JPG, JPEG o PNG")
    private String imageUrl = "/uploads/default-profile.png";

    @Schema(description = "Thumbnail of the image", example = "https://www.example.com/image.jpg")
    @Column(name = "imageThumbnailUrl", nullable = false, length = 500)
    @Size(max = 500, message = "La URL de la miniatura debe tener como máximo 255 caracteres")
    @NotBlank(message = "La URL de la miniatura no puede estar vacía y debe contener al menos un carácter que no sea un espacio en blanco")
    @Pattern(regexp = "^(http|https)://.*\\.(jpg|jpeg|png)$",
            message = "La URL de la miniatura debe ser una URL de imagen válida que termine en JPG, JPEG o PNG")
    private String imageThumbnailUrl = "/uploads/default-profile.png";

    public ImageProfileModel(Integer idImageProfile, ProfileModel profile, String imageUrl, String imageThumbnailUrl) {
        this.idImageProfile = idImageProfile;
        this.profile = profile;
        this.imageUrl = imageUrl;
        this.imageThumbnailUrl = imageThumbnailUrl;
    }

    public ImageProfileModel() {
    }

    public Integer getIdImageProfile() {
        return idImageProfile;
    }

    public void setIdImageProfile(Integer idImageProfile) {
        this.idImageProfile = idImageProfile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageThumbnailUrl() {
        return imageThumbnailUrl;
    }

    public void setImageThumbnailUrl(String imageThumbnailUrl) {
        this.imageThumbnailUrl = imageThumbnailUrl;
    }

    public ProfileModel getProfile() {
        return profile;
    }

    public void setProfile(ProfileModel Profile) {
        this.profile = Profile;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ImageProfileModel{");
        sb.append("idImageProfile=").append(idImageProfile);
        sb.append(", profile=").append(profile);
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", imageThumbnailUrl='").append(imageThumbnailUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }

}