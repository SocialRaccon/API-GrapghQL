package itst.socialraccoon.api.repositories;

import itst.socialraccoon.api.models.compositekeys.ReactionPK;
import itst.socialraccoon.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import itst.socialraccoon.api.models.ReactionModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface ReactionRepository extends JpaRepository<ReactionModel, ReactionPK> {

    @Query(value = "SELECT * FROM reaction WHERE idPost = :post_id", nativeQuery = true)
    List<ReactionModel> getReactionsByPostId(@Param("post_id") int post_id);

    @Query(value = "SELECT * FROM reaction WHERE idPost = :post_id", nativeQuery = true)
    List<ReactionModel> getReactionsByPostId(@Param("post_id") int post_id, Pageable pageable);

    @Query(value = "SELECT * FROM reaction WHERE idUser = :user_id", nativeQuery = true)
    List<ReactionModel> getReactionsByUserId(@Param("user_id") int user_id);

    @Query(value = "SELECT * FROM reaction WHERE idUser = :user_id", nativeQuery = true)
    List<ReactionModel> getReactionsByUserId(@Param("user_id") int user_id, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM reaction WHERE idPost = :post_id", nativeQuery = true)
    Integer getReactionCountByPostId(@Param("post_id") int post_id);

    //getReactionByPostIdAndUserId
    @Query(value = "SELECT * FROM reaction WHERE idPost = :post_id AND idUser = :user_id", nativeQuery = true)
    ReactionModel getReactionByPostIdAndUserId(@Param("post_id") int post_id, @Param("user_id") int user_id);

    //getReactionsByPostIdAndReactionType
    @Query(value = "SELECT * FROM reaction WHERE idPost = :post_id AND idReactionType = :reaction_type_id", nativeQuery = true)
    List<ReactionModel> getReactionsByPostIdAndReactionType(@Param("post_id") int post_id, @Param("reaction_type_id") int reaction_type_id);

    //getReactionsByUserIdAndReactionType
    @Query(value = "SELECT * FROM reaction WHERE idUser = :user_id AND idReactionType = :reaction_type_id", nativeQuery = true)
    List<ReactionModel> getReactionsByUserIdAndReactionType(@Param("user_id") int user_id, @Param("reaction_type_id") int reaction_type_id);

    //getReactionCountByPostIdAndReactionType
    @Query(value = "SELECT COUNT(*) FROM reaction WHERE idPost = :post_id AND idReactionType = :reaction_type_id", nativeQuery = true)
    Integer getReactionCountByPostIdAndReactionType(@Param("post_id") int post_id, @Param("reaction_type_id") int reaction_type_id);

    void deleteByIdUser(UserModel idUser);

}
