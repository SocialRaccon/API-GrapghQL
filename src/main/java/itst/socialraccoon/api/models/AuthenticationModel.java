package itst.socialraccoon.api.models;

import jakarta.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "authentication")
@Schema(description = "Model representing an authentication")
public class AuthenticationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the authentication", example = "1")
    private Integer idAuthentication;

    @Column(nullable = false, unique = true)
    @Schema(description = "Email of the user", example = "josuejoss10@gmail.com")
    private String email;

    @Schema(description = "Password of the user", example = "1234!#$")
    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "authentication", fetch = FetchType.LAZY)
    private UserModel user;


}
