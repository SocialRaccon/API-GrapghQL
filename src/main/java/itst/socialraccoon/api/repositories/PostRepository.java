package itst.socialraccoon.api.repositories;

import itst.socialraccoon.api.models.PostModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostModel, Integer> {

    @Query(value = "SELECT * FROM post WHERE idUser = :idUser LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<PostModel> findByUser(@Param("idUser") Integer idUser, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @Query("""
        SELECT p FROM PostModel p 
        WHERE p.user.idUser = :userId 
        ORDER BY p.dateCreated DESC
        """)
    List<PostModel> findByUser(
            @Param("userId") Integer userId,
            Pageable pageable
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM PostModel p WHERE p.user.idUser = :userId AND p.idPost = :postId")
    void deleteByUserAndPost(
            @Param("userId") Integer userId,
            @Param("postId") Integer postId
    );

    Optional<PostModel> findByUserIdUserAndIdPost(Integer userId, Integer postId);

    Page<PostModel> findAllByOrderByDateCreatedDesc(Pageable pageable);

    @Query(value = "SELECT * FROM post WHERE idUser = :idUser AND idPost = :idPost", nativeQuery = true)
    PostModel findByUserAndPost(@Param("idUser") Integer idUser, @Param("idPost") Integer idPost);

    @Query(value = "SELECT * FROM post WHERE idUser = :idUser", nativeQuery = true)
    Page<PostModel> findByUserAndPost(@Param("idUser") Integer idUser, Pageable pageable);

}