package itst.socialraccoon.api.controllers;

import itst.socialraccoon.api.models.ProfileModel;
import itst.socialraccoon.api.models.PostModel;
import itst.socialraccoon.api.services.ProfileService;
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

    @QueryMapping
    public ProfileModel getProfileById(@Argument Integer id) {
        ProfileModel profile = profileService.findById(id);
        if (profile == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Profile not found with id: " + id);
        }
        return profile;
    }

    @QueryMapping
    public ProfileModel getProfileByControlNumber(@Argument String controlNumber) {
        return profileService.findByUserControlNumber(controlNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Profile not found with control number: " + controlNumber));
    }

    @QueryMapping
    public List<PostModel> getProfilePosts(
            @Argument Integer profileId,
            @Argument @DefaultValue("10") Integer limit,
            @Argument @DefaultValue("0") Integer offset) {
        if (limit < 0 || offset < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Limit and offset must be non-negative");
        }
        return profileService.findPostsByProfileId(profileId, limit, offset);
    }

    @QueryMapping
    public List<ProfileModel> searchProfiles(
            @Argument String query,
            @Argument @DefaultValue("10") Integer limit) {
        if (query == null || query.trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Search query cannot be empty");
        }
        return profileService.searchProfiles(query, limit);
    }
}
