package itst.socialraccoon.api.controllers;

import itst.socialraccoon.api.models.ProfileModel;
import itst.socialraccoon.api.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @QueryMapping
    public List<ProfileModel> getAll() {
        return profileService.findAll();
    }
}