package itst.socialraccoon.api.dtos;

import java.sql.Timestamp;

public class CommentDTO {
    private Integer idComment;
    private Integer idUser;
    private Integer idPost;
    private String comment;
    private Timestamp date;

    public CommentDTO() {
    }

    public CommentDTO(Integer idComment, Integer idUser, Integer idPost, String comment, Timestamp date) {
        this.idComment = idComment;
        this.idUser = idUser;
        this.idPost = idPost;
        this.comment = comment;
        this.date = date;
    }

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
