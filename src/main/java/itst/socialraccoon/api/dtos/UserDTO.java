package itst.socialraccoon.api.dtos;

public class UserDTO {
    private Integer idUser;
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String controlNumber;
    private String careerName;

    public UserDTO() {
    }

    public UserDTO(Integer idUser, String name, String lastName, String secondLastName, String email, String controlNumber, String career) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.email = email;
        this.controlNumber = controlNumber;
        this.careerName = career;
    }

    // Getters and Setters

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }
}