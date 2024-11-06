package itst.socialraccoon.api.repositories;

import itst.socialraccoon.api.models.ProfileModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProfileRepository extends JpaRepository<ProfileModel, Integer> {
    Optional<ProfileModel> findByIdUser_ControlNumber(String controlNumber);

    @Query("""
        SELECT p FROM ProfileModel p 
        JOIN p.idUser u 
        WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :query, '%'))
        OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
        OR LOWER(u.controlNumber) LIKE LOWER(CONCAT('%', :query, '%'))
        """)
    List<ProfileModel> searchProfiles(
            @Param("query") String query,
            Pageable pageable
    );

    @Query("""
        SELECT COUNT(p) 
        FROM PostModel p 
        WHERE p.user.idUser = :profileId
        """)
    Integer getPostCountByProfile(@Param("profileId") Integer profileId);
}