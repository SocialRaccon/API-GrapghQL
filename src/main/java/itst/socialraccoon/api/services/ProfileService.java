package itst.socialraccoon.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import itst.socialraccoon.api.models.PostModel;
import itst.socialraccoon.api.models.ProfileModel;
import itst.socialraccoon.api.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import itst.socialraccoon.api.repositories.ProfileRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProfileModel> findAll() {
        return profileRepository.findAll();
    }

    public ProfileModel save(ProfileModel profile) {
        return profileRepository.save(profile);
    }

    public ProfileModel findById(Integer id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Profile not found with id: " + id));
    }

    public Optional<ProfileModel> findByUserControlNumber(String controlNumber) {
        return profileRepository.findByIdUser_ControlNumber(controlNumber);
    }

    public List<PostModel> findPostsByProfileId(Integer profileId, Integer limit, Integer offset) {
        // First check if profile exists
        ProfileModel profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NoSuchElementException("Profile not found with id: " + profileId));

        try {
            // If profile exists, get their posts
            return postRepository.findByUser(profile.getIdUser().getIdUser(), limit, offset);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching posts for profile: " + profileId, e);
        }
    }

    public List<ProfileModel> searchProfiles(String query, Integer limit) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be empty");
        }
        return profileRepository.searchProfiles(query, PageRequest.of(0, limit));
    }

    public ProfileModel getProfileWithStats(Integer profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new NoSuchElementException("Profile not found with id: " + profileId));
    }
}