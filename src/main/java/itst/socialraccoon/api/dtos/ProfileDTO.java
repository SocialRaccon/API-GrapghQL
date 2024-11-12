package itst.socialraccoon.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import itst.socialraccoon.api.models.ImageProfileModel;

import java.util.List;

@Schema(description = "Data Transfer Object representing a user profile")
public class ProfileDTO {

    @Schema(description = "Unique identifier of the profile", example = "1")
    private Integer idProfile;

    @Schema(description = "Description of the profile", example = "This is a description")
    private String description;

    @Schema(description = "Name of the user", example = "Juan")
    private String userName;

    @Schema(description = "Unique identifier of the user", example = "1")
    private Integer idUser;

    @Schema(description = "Images of the profile")
    private List<ImageProfileModel> images;

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer id) {
        this.idProfile = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public List<ImageProfileModel> getImages() {
        return images;
    }

    public void setImages(List<ImageProfileModel> images) {
        this.images = images;
    }

    public ProfileDTO() {
    }

    public ProfileDTO(Integer idProfile, String description, String userName, Integer idUser, List<ImageProfileModel> images) {
        this.idProfile = idProfile;
        this.description = description;
        this.userName = userName;
        this.idUser = idUser;
        this.images = images;
    }
}