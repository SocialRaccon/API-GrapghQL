package itst.social_raccoon_api.Dto;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class PostDTO {
    private String description;
    private List<String>  ImageUrls;
    private String UserId;
    private Date fecha;
    private Image image;

    public PostDTO(String description, List<String> imageUrls, String userId, Date fecha, Image image) {
        this.description = description;
        ImageUrls = imageUrls;
        UserId = userId;
        this.fecha = fecha;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Getters and setters
    public List<String> getImageUrls() { return ImageUrls;}

    public void setImageUrls(List<String> imageUrls) {ImageUrls = imageUrls;}

    public String getUserId() {return UserId;}

    public void setUserId(String userId) {UserId = userId;}

    public Date getFecha() {return fecha;}

    public void setFecha(Date fecha) {this.fecha = fecha;}

    public Image getImage() {return image;}

    public void setImage(Image image) {this.image = image;}
}