package itst.social_raccoon_api.Services;

import java.awt.print.Pageable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



import itst.social_raccoon_api.Dto.RelationshipDTO;
import itst.social_raccoon_api.Dto.RelationshipInfoDTO;
import itst.social_raccoon_api.Models.RelationshipModel;
import itst.social_raccoon_api.Models.UserModel;
import itst.social_raccoon_api.Repositories.RelationshipRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class RelationshipService {

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    private RelationshipRepository relationshipRepository;

    public void followUser(Integer userId, Integer followerId) {
        UserModel user = userService.findById(userId);
        UserModel follower = userService.findById(followerId);

        if (user != null && follower != null) {
            RelationshipModel relationshipModel = new RelationshipModel(user, follower);
            relationshipRepository.save(relationshipModel);
        }
    }

    public void unfollowUser(Integer userId, Integer followerId) {
        UserModel user = userService.findById(userId);
        UserModel follower = userService.findById(followerId);

        if (user != null && follower != null) {
            RelationshipModel relationshipModel = new RelationshipModel(user, follower);
            relationshipRepository.delete(relationshipModel);
        }
    }

    public RelationshipDTO getFollowersAndFollowing(Integer userId) {
        List<RelationshipModel> followers = relationshipRepository.getFollowersByUserId(userId);
        List<RelationshipModel> following = relationshipRepository.getFollowersByFollowerId(userId);

        if (followers.isEmpty() && following.isEmpty()) {
            throw new NoSuchElementException("No followers or following found for the user with ID: " + userId);
        }

        RelationshipDTO relationshipDTO = new RelationshipDTO();
        relationshipDTO.setFollowers(followers.stream().map(this::convertToFollowerInfoDTO).collect(Collectors.toList()));
        relationshipDTO.setFollowing(following.stream().map(this::convertToFollowerInfoDTO).collect(Collectors.toList()));
        return relationshipDTO;
    }

    private RelationshipInfoDTO convertToFollowerInfoDTO(RelationshipModel relationshipModel) {
        // Assuming you want to get the user information that FOLLOWS the current user
        UserModel followerUser = relationshipModel.getFollowerUser();
        return new RelationshipInfoDTO(followerUser.getIdUser(), followerUser.getName());
    }

    public List<RelationshipModel> getFollowersByUserId(Integer userId, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return relationshipRepository.getFollowersByUserId(userId, (Pageable) pageRequest);
    }

    public List<RelationshipModel> getFollowersByFollowerId(Integer followerId, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return relationshipRepository.getFollowersByFollowerId(followerId, (Pageable) pageRequest);
    }
}