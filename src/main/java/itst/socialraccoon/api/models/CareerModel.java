package itst.socialraccoon.api.models;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "career")
@Schema(description = "Model representing a career")
public class CareerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCareer", nullable = false)
    @Schema(description = "Unique identifier of the career", example = "1")
    private Integer idCareer;

    @Size(max = 65)
    @NotNull
    @Column(name = "name", nullable = false, length = 65)
    @Schema(description = "Name of the career", example = "Computer Science")
    private String name;

    @Size(max = 10)
    @NotNull
    @Column(name = "acronym", nullable = false, length = 10)
    @Schema(description = "Acronym of the career", example = "CS")
    private String acronym;


}