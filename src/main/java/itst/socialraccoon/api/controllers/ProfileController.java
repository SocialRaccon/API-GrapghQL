package itst.socialraccoon.api.controllers;

import itst.socialraccoon.api.dtos.PostDTO;
import itst.socialraccoon.api.dtos.ProfileDTO;
import itst.socialraccoon.api.models.ProfileModel;
import itst.socialraccoon.api.models.PostModel;
import itst.socialraccoon.api.services.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ModelMapper modelMapper;

    @QueryMapping
    public ProfileDTO getProfileById(@Argument Integer id) {
        ProfileModel profile = profileService.findById(id);
        if (profile == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Profile not found with id: " + id);
        }
        return convertToDTO(profile);
    }

    @QueryMapping
    public ProfileDTO getProfileByControlNumber(@Argument String controlNumber) {
        return profileService.findByUserControlNumber(controlNumber)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Profile not found with control number: " + controlNumber));
    }

    @QueryMapping
    public List<PostDTO> getProfilePosts(
            @Argument Integer profileId,
            @Argument @DefaultValue("10") Integer limit,
            @Argument @DefaultValue("0") Integer offset) {
        if (limit < 0 || offset < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Limit and offset must be non-negative");
        }
        List<PostModel> posts = profileService.findPostsByProfileId(profileId, limit, offset);
        return posts.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @QueryMapping
    public List<ProfileDTO> searchProfiles(
            @Argument String query,
            @Argument @DefaultValue("10") Integer limit) {
        if (query == null || query.trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Search query cannot be empty");
        }
        List<ProfileModel> profiles = profileService.searchProfiles(query, limit);
        return profiles.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private PostDTO convertToDTO(PostModel post) {
        PostDTO dto = modelMapper.map(post, PostDTO.class);
        return dto;
    }

    private ProfileDTO convertToDTO(ProfileModel profile) {
        ProfileDTO dto = modelMapper.map(profile, ProfileDTO.class);
        return dto;
    }
}
