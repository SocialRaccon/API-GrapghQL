package itst.social_raccoon_api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import itst.social_raccoon_api.dtos.AuthenticationDTO;
import itst.social_raccoon_api.models.AuthenticationModel;
import itst.social_raccoon_api.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("authentications")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Authentications", description = "Provides methods to manage authentications.")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(
            summary = "Login",
            description = "Login user",
            tags = {"Authentications"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User email and password",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AuthenticationModel.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Login",
                                    value = "{\n"
                                            + "  \"email\": \"alex2227@hotmail.com\",\n"
                                            + "  \"password\": \"password\"\n"
                                            + "}"
                            )
                    )
            )
    )
    public ResponseEntity<String> login(@RequestBody AuthenticationModel authentication) {
        AuthenticationModel authenticationOptional = authenticationService.findByEmail(authentication.getEmail());
        if (authenticationOptional != null) {
            if (authenticationOptional.getPassword().equals(authentication.getPassword())) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        } else {
            return ResponseEntity.badRequest().body("Email not found");
        }
    }

    @PostMapping("/recover")
    @Operation(
            summary = "Recover password",
            description = "Recover user password",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User email",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AuthenticationModel.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Recover",
                                    value = "{\n"
                                            + "  \"email\": \"alex2227@hotmail.com \"\n"
                                            + "}"
                            )
                    )
            )
    )
    public ResponseEntity<String> recoverPassword(@RequestBody AuthenticationModel authentication) {
        AuthenticationModel authenticationOptional = authenticationService.findByEmail(authentication.getEmail());
        if (authenticationOptional != null) {
            return ResponseEntity.ok("Password recovery email sent");
        } else {
            return ResponseEntity.badRequest().body("Email not found");
        }
    }

    @PutMapping("/change")
    @Operation(summary = "Change password", description = "Change user password")
    public ResponseEntity<String> changePassword(@RequestBody AuthenticationDTO authenticationDTO) {
        AuthenticationModel authenticationModel = authenticationService.findByEmail(authenticationDTO.getEmail());
        if (authenticationModel != null) {
            if (authenticationModel.getPassword().equals(authenticationDTO.getPassword())) {
                authenticationModel.setPassword(authenticationDTO.getNewPassword());
                authenticationService.save(authenticationModel);
                return ResponseEntity.ok("Password changed successfully");
            } else {
                return ResponseEntity.badRequest().body("Incorrect password");
            }
        } else {
            return ResponseEntity.badRequest().body("Email not found");
        }
    }

}